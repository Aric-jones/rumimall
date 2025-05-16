package com.rumi.search.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.rumi.common.entity.Result;
import com.rumi.goods.feign.SkuFeign;
import com.rumi.goods.pojo.Sku;
import com.rumi.search.dao.SkuEsMapper;
import com.rumi.search.pojo.SkuInfo;
import com.rumi.search.service.ISkuService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @ClassName: SkuServiceImpl
 * @Description:
 * @Author: CSH
 * @Date: 2025-05-14 21:05
 */
@Service
public class SkuServiceImpl implements ISkuService {

    @Autowired
    private SkuFeign skuFeign;
    @Autowired
    private SkuEsMapper skuEsMapper;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 导出数据到ES
     */
    @Override
    public void importDate() {
        // 调用Feign，查询List<Sku>
        Result<List<Sku>> skuResult = skuFeign.findByStatus("1");

        // 将list<Sku>转成list<SkuInfo>
        List<SkuInfo> skuInfos = BeanUtil.copyToList(skuResult.getData(), SkuInfo.class);

        // 生成动态规格域
        for (SkuInfo skuInfo : skuInfos) {
            Map<String, Object> map = JSON.parseObject(skuInfo.getSpec());
            skuInfo.setSpecMap(map);
        }

        // 调用dao实现数据批量导入
        skuEsMapper.saveAll(skuInfos);
    }

    /**
     * 搜索
     *
     * @param searchMap
     */
    @Override
    public Map<String, Object> search(Map<String, Object> searchMap) {

        // 构建查询条件并查询
        AggregatedPage<SkuInfo> page = buildQueryAndSearch(searchMap);

        // 获取聚合结果
        List<String> categoryList = makeTheList(page, "skuCategory");
        List<String> brandList = makeTheList(page, "skuBrand");
        Map<String, Set<String>> specMap = makeTheListForSpec(page, "skuSpec");

        // 构建返回结果
        Map<String, Object> resultMap = searchList(page);
        if (Objects.isNull(searchMap) || Objects.isNull(searchMap.get("category"))) {
            resultMap.put("categoryList", categoryList);
        }
        if (Objects.isNull(searchMap) || Objects.isNull(searchMap.get("brand"))) {
            resultMap.put("brandList", brandList);
        }
        resultMap.put("specMap", specMap);
        return resultMap;
    }

    private Map<String, Set<String>> makeTheListForSpec(AggregatedPage<SkuInfo> page, String skuSpec) {
        // 处理聚合结果
        List<String> list = this.makeTheList(page, "skuSpec");

        Map<String, Set<String>> allSpec = new HashMap<>();
        // 将spec转成对象
        for (String spec : list) {
            Map<String, String> specMap = JSONUtil.toBean(spec, new TypeReference<Map<String, String>>() {
            }, false);
            for (String key : specMap.keySet()) {
                String value = specMap.get(key);
                Set<String> set = allSpec.get(key);
                if (set == null) {
                    set = new HashSet<>();
                }
                set.add(value);
                allSpec.put(key, set);
            }
        }
        return allSpec;
    }

    private List<String> makeTheList(AggregatedPage<SkuInfo> page, String field) {

        // 处理聚合结果
        StringTerms stringTermsCategory = (StringTerms) page.getAggregation(field);
        List<String> list = new ArrayList<>();
        if (stringTermsCategory != null) {
            for (StringTerms.Bucket bucket : stringTermsCategory.getBuckets()) {
                String keyAsString = bucket.getKeyAsString();
                System.out.println(keyAsString);
                list.add(keyAsString);
            }
        }
        return list;
    }

    private static Map<String, Object> searchList(AggregatedPage<SkuInfo> page) {
        // 构建返回结果
        List<SkuInfo> content = page.getContent();
        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", content);
        resultMap.put("total", totalElements);
        resultMap.put("totalPages", totalPages);
        return resultMap;
    }

    private AggregatedPage<SkuInfo> buildQueryAndSearch(Map<String, Object> searchMap) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (searchMap != null && !searchMap.isEmpty()) {
            // 使用关键词进行搜索
            String keywords = (String) searchMap.get("keywords");
            // 如果关键词不为空
            if (!StringUtils.isEmpty(keywords)) {
                boolQueryBuilder.must(QueryBuilders.queryStringQuery(keywords).field("name"));
            }
            if (Objects.nonNull(searchMap.get("category"))) {
                boolQueryBuilder.must(QueryBuilders.termQuery("categoryName", searchMap.get("category")));
            }
            if (Objects.nonNull(searchMap.get("brand"))) {
                boolQueryBuilder.must(QueryBuilders.termQuery("brandName", searchMap.get("brand")));
            }
            for (String key : searchMap.keySet()) {
                if (key.startsWith("spec_")) {
                    boolQueryBuilder.must(QueryBuilders.termQuery("specMap." + key.substring(5) + ".keyword", searchMap.get(key)));
                }
            }

            //价格过滤查询
            String price = (String) searchMap.get("price");
            if (!StringUtils.isEmpty(price)) {
                price = price.replace("元", "").replace("以上", "");
                String[] split = price.split("-");
                if (!price.isEmpty()) {
                    boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gt(Integer.parseInt(split[0])));
                }
                if (split.length > 1) {
                    boolQueryBuilder.must(QueryBuilders.rangeQuery("price").lte(Integer.parseInt(split[1])));
                }
            }

            //构建排序查询
            String sortRule = (String) searchMap.get("sortRule");
            String sortField = (String) searchMap.get("sortField");
            if (!StringUtils.isEmpty(sortRule) && !StringUtils.isEmpty(sortField)) {
                nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(sortField).order("DESC".equals(sortRule) ? SortOrder.DESC : SortOrder.ASC));
            }
        }


        /*
          构建聚合查询获取分类列表
          terms("skuCategory"): 聚合的名称，相当于别名
          field("categoryName"): 要聚合字段
          size(50): 聚合结果集的大小
         */
        if (Objects.isNull(searchMap) || Objects.isNull(searchMap.get("category"))) {
            nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuCategory").field("categoryName").size(2000));
        }
        if (Objects.isNull(searchMap) || Objects.isNull(searchMap.get("brand"))) {
            nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuBrand").field("brandName").size(2000));
        }
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuSpec").field("spec.keyword").size(2000));

        // 分页查询
        int pageNum = getPage(searchMap);
        int size = 3;
        nativeSearchQueryBuilder.withPageable(PageRequest.of(pageNum - 1, size));
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);

        // 设置高亮
        HighlightBuilder.Field field = new HighlightBuilder.Field("name");
        field.preTags("<span style='color:red'>");
        field.postTags("</span>");
        field.fragmentSize(100);
        nativeSearchQueryBuilder.withHighlightFields(field);

        return elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), SkuInfo.class, new SearchResultMapperImpl());
    }

    private int getPage(Map<String, Object> searchMap) {
        if (searchMap != null) {
            String pageNum = (String) searchMap.get("pageNum");
            try {
                return Integer.parseInt(pageNum);
            } catch (NumberFormatException e) {
                return 1;
            }
        }
        return 1;
    }


}
