package com.rumi.search.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.rumi.common.entity.Result;
import com.rumi.goods.feign.SkuFeign;
import com.rumi.goods.pojo.Sku;
import com.rumi.search.dao.SkuEsMapper;
import com.rumi.search.pojo.SkuInfo;
import com.rumi.search.service.ISkuService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
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
            Map<String, Object> map = (Map<String, Object>) JSON.parse(skuInfo.getSpec());
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

        // 使用关键词进行搜索
        String keywords = (String) searchMap.get("keywords");

        if (StringUtils.isEmpty(keywords)) {
            keywords = "华为";
        }

        // 构建搜索条件
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        /**
         * 构建聚合查询获取分类列表
         * terms("skuCategory"): 聚合的名称，相当于别名
         * field("categoryName"): 要聚合字段
         * size(50): 聚合结果集的大小
         */
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuCategory").field("categoryName").size(50));
        /**
         * 设置主查询条件
         * withQuery()：设置主查询条件，需要传入一个查询条件
         * QueryBuilders.matchQuery("name",keywords)：构建一个 match 查询，用于在 name 字段中搜索包含 keywords 的文档
         */
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("name",keywords));
        // 执行查询
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        AggregatedPage<SkuInfo> skuInfos = elasticsearchTemplate.queryForPage(nativeSearchQuery, SkuInfo.class);

        // 处理聚合结果
        StringTerms stringTermsCategory = (StringTerms) skuInfos.getAggregation("skuCategory");
        List<String> categoryList = new ArrayList<>();
        if (stringTermsCategory != null) {
            for (StringTerms.Bucket bucket : stringTermsCategory.getBuckets()) {
                String keyAsString = bucket.getKeyAsString();
                System.out.println(keyAsString);
                categoryList.add(keyAsString);
            }
        }

        // 构建返回结果
        List<SkuInfo> content = skuInfos.getContent();
        int totalPages = skuInfos.getTotalPages();
        long totalElements = skuInfos.getTotalElements();
        Map<String,Object> resultMap =new HashMap<>();
        resultMap.put("categoryList",categoryList);
        resultMap.put("rows",content);
        resultMap.put("total",totalElements);
        resultMap.put("totalPages",totalPages);
        return resultMap;
    }
}
