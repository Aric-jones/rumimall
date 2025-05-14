package com.rumi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rumi.dao.CategoryBrandMapper;
import com.rumi.goods.pojo.CategoryBrand;
import com.rumi.service.ICategoryBrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author CSH
 * @since 2025-05-07
 */
@Service
public class CategoryBrandServiceImpl extends ServiceImpl<CategoryBrandMapper, CategoryBrand>
        implements ICategoryBrandService {

    @Override
    public IPage<CategoryBrand> findPage(CategoryBrand categoryBrand, int page, int size) {
        LambdaQueryWrapper<CategoryBrand> wrapper = buildQueryWrapper(categoryBrand);
        Page<CategoryBrand> pageParam = new Page<>(page, size);
        return this.page(pageParam, wrapper);
    }

    @Override
    public IPage<CategoryBrand> findPage(int page, int size) {
        Page<CategoryBrand> pageParam = new Page<>(page, size);
        return this.page(pageParam);
    }

    @Override
    public List<CategoryBrand> findList(CategoryBrand categoryBrand) {
        LambdaQueryWrapper<CategoryBrand> wrapper = buildQueryWrapper(categoryBrand);
        return this.list(wrapper);
    }

    @Override
    @Transactional
    public void deleteByCategoryAndBrand(Integer categoryId, Integer brandId) {
        LambdaQueryWrapper<CategoryBrand> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CategoryBrand::getCategoryId, categoryId)
                .eq(CategoryBrand::getBrandId, brandId);
        this.remove(wrapper);
    }

    private LambdaQueryWrapper<CategoryBrand> buildQueryWrapper(CategoryBrand categoryBrand) {
        return new LambdaQueryWrapper<CategoryBrand>()
                .eq(Objects.nonNull(categoryBrand.getCategoryId()),
                        CategoryBrand::getCategoryId, categoryBrand.getCategoryId())
                .eq(Objects.nonNull(categoryBrand.getBrandId()),
                        CategoryBrand::getBrandId, categoryBrand.getBrandId());
    }
}