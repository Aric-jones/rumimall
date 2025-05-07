package com.rumi.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.pojo.CategoryBrand;
import com.rumi.service.ICategoryBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CSH
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/category-brand")
public class CategoryBrandController {
    @Autowired
    private ICategoryBrandService categoryBrandService;

    /**
     * 分页条件查询
     */
    @PostMapping("/search/{page}/{size}")
    public Result<IPage<CategoryBrand>> findPage(
            @RequestBody(required = false) CategoryBrand categoryBrand,
            @PathVariable int page,
            @PathVariable int size) {
        IPage<CategoryBrand> pageInfo = categoryBrandService.findPage(categoryBrand, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * 简单分页查询
     */
    @GetMapping("/search/{page}/{size}")
    public Result<IPage<CategoryBrand>> findPage(
            @PathVariable int page,
            @PathVariable int size) {
        IPage<CategoryBrand> pageInfo = categoryBrandService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * 条件查询列表
     */
    @PostMapping("/search")
    public Result<List<CategoryBrand>> findList(
            @RequestBody(required = false) CategoryBrand categoryBrand) {
        List<CategoryBrand> list = categoryBrandService.findList(categoryBrand);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 根据ID组合删除（categoryId + brandId）
     */
    @DeleteMapping("/{categoryId}/{brandId}")
    public Result<String> delete(
            @PathVariable Integer categoryId,
            @PathVariable Integer brandId) {
        categoryBrandService.deleteByCategoryAndBrand(categoryId, brandId);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 添加
     */
    @PostMapping
    public Result<String> add(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.save(categoryBrand);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 查询全部
     */
    @GetMapping
    public Result<List<CategoryBrand>> findAll() {
        List<CategoryBrand> list = categoryBrandService.list();
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }
}
