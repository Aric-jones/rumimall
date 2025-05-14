package com.rumi.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rumi.goods.pojo.Sku;

import java.util.List;

/**
 * @author CSH
 * @since 2025-05-07
 */
public interface ISkuService extends IService<Sku> {
    /**
     * 分页条件查询
     */
    IPage<Sku> findPage(Sku sku, int page, int size);

    /**
     * 简单分页查询
     */
    IPage<Sku> findPage(int page, int size);

    /**
     * 条件查询列表
     */
    List<Sku> findList(Sku sku);

    /**
     * 根据状态查询
     */
    List<Sku> findByStatus(String status);
}
