package com.rumi.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rumi.pojo.CategoryBrand;

import java.util.List;

/**
 * @author CSH
 * @since 2025-05-07
 */
public interface ICategoryBrandService extends IService<CategoryBrand> {
    /**
     * 分页条件查询
     */
    IPage<CategoryBrand> findPage(CategoryBrand categoryBrand, int page, int size);

    /**
     * 简单分页查询
     */
    IPage<CategoryBrand> findPage(int page, int size);

    /**
     * 条件查询列表
     */
    List<CategoryBrand> findList(CategoryBrand categoryBrand);

    /**
     * 批量删除（根据categoryId和brandId组合）
     */
    void deleteByCategoryAndBrand(Integer categoryId, Integer brandId);
}
