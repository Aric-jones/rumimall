package com.rumi.content.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.content.service.IContentCategoryService;
import com.rumi.content.pojo.ContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CSH
 * @since 2025-05-13
 */
@RestController
@RequestMapping("/contentCategory")
@CrossOrigin
public class ContentCategoryController {

    @Autowired
    private IContentCategoryService contentCategoryService;

    /**
     * 分页条件查询
     */
    @PostMapping("/search/{page}/{size}")
    public Result<Page<ContentCategory>> findPage(
            @RequestBody(required = false) ContentCategory contentCategory,
            @PathVariable int page,
            @PathVariable int size) {
        Page<ContentCategory> pageResult = contentCategoryService.findPage(contentCategory, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageResult);
    }

    /**
     * 分页查询
     */
    @GetMapping("/search/{page}/{size}")
    public Result<Page<ContentCategory>> findPage(
            @PathVariable int page,
            @PathVariable int size) {
        Page<ContentCategory> pageResult = contentCategoryService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageResult);
    }

    /**
     * 条件查询
     */
    @PostMapping("/search")
    public Result<List<ContentCategory>> findList(@RequestBody(required = false) ContentCategory contentCategory) {
        List<ContentCategory> list = contentCategoryService.findList(contentCategory);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        contentCategoryService.removeById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 修改
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody ContentCategory contentCategory, @PathVariable Long id) {
        contentCategory.setId(id);
        contentCategoryService.updateById(contentCategory);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 新增
     */
    @PostMapping
    public Result add(@RequestBody ContentCategory contentCategory) {
        contentCategoryService.save(contentCategory);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    public Result<ContentCategory> findById(@PathVariable Long id) {
        ContentCategory contentCategory = contentCategoryService.getById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", contentCategory);
    }

    /**
     * 查询全部
     */
    @GetMapping
    public Result<List<ContentCategory>> findAll() {
        List<ContentCategory> list = contentCategoryService.list();
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }
}