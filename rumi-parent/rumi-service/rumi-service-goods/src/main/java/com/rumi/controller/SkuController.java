package com.rumi.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.pojo.Sku;
import com.rumi.service.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CSH
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/sku")
public class SkuController {
    @Autowired
    private ISkuService skuService;

    /**
     * 分页条件查询
     */
    @PostMapping("/search/{page}/{size}")
    public Result<IPage<Sku>> findPage(
            @RequestBody(required = false) Sku sku,
            @PathVariable int page,
            @PathVariable int size) {
        IPage<Sku> pageInfo = skuService.findPage(sku, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * 简单分页查询
     */
    @GetMapping("/search/{page}/{size}")
    public Result<IPage<Sku>> findPage(
            @PathVariable int page,
            @PathVariable int size) {
        IPage<Sku> pageInfo = skuService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * 条件查询列表
     */
    @PostMapping("/search")
    public Result<List<Sku>> findList(@RequestBody(required = false) Sku sku) {
        List<Sku> list = skuService.findList(sku);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    public Result<Sku> findById(@PathVariable Long id) {
        Sku sku = skuService.getById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", sku);
    }

    /**
     * 添加SKU
     */
    @PostMapping
    public Result<String> add(@RequestBody Sku sku) {
        skuService.save(sku);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改SKU
     */
    @PutMapping("/{id}")
    public Result<String> update(@RequestBody Sku sku, @PathVariable Long id) {
        sku.setId(id);
        boolean update = skuService.updateById(sku);
        if (!update) {
            return new Result<>(false, StatusCode.ERROR, "修改失败");
        }
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除SKU
     */
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        skuService.removeById(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/batch")
    public Result<String> batchDelete(@RequestBody List<Long> ids) {
        skuService.removeByIds(ids);
        return new Result<>(true, StatusCode.OK, "批量删除成功");
    }

    /**
     * 上架/下架操作
     */
    @PutMapping("/{id}/status/{status}")
    public Result<String> updateStatus(
            @PathVariable Long id,
            @PathVariable String status) {
        Sku sku = new Sku();
        sku.setId(id);
        sku.setStatus(status);
        skuService.updateById(sku);
        return new Result<>(true, StatusCode.OK, "状态更新成功");
    }
}
