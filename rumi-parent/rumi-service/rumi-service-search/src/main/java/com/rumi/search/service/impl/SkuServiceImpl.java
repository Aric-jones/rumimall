package com.rumi.search.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.rumi.common.entity.Result;
import com.rumi.goods.feign.SkuFeign;
import com.rumi.goods.pojo.Sku;
import com.rumi.search.dao.SkuEsMapper;
import com.rumi.search.pojo.SkuInfo;
import com.rumi.search.service.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        for(SkuInfo skuInfo : skuInfos){
            Map<String,Object> map = (Map<String,Object>)JSON.parse(skuInfo.getSpec());
            skuInfo.setSpecMap(map);
        }

        // 调用dao实现数据批量导入
        skuEsMapper.saveAll(skuInfos);
    }
}
