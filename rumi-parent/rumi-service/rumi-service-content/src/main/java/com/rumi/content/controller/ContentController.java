package com.rumi.content.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.content.service.IContentService;
import com.rumi.content.pojo.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CSH
 * @since 2025-05-13
 */
@RestController
@RequestMapping("/content")
@CrossOrigin
public class ContentController {

    @Autowired
    private IContentService contentService;

    /**
     * Content分页条件搜索实现
     * @param content 查询条件
     * @param page 当前页
     * @param size 每页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<Page<Content>> findPage(@RequestBody(required = false) Content content, @PathVariable int page, @PathVariable int size) {
        Page<Content> pageResult = contentService.findPage(content, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageResult);
    }

    /**
     * Content分页搜索实现
     * @param page 当前页
     * @param size 每页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<Page<Content>> findPage(@PathVariable int page, @PathVariable int size) {
        Page<Content> pageResult = contentService.page(new Page<>(page, size));
        return new Result<>(true, StatusCode.OK, "查询成功", pageResult);
    }

    /**
     * 多条件搜索品牌数据
     * @param content 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search")
    public Result<List<Content>> findList(@RequestBody(required = false) Content content) {
        List<Content> list = contentService.findList(content);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }



    /**
     * 根据ID删除品牌数据
     * @param id 主键ID
     * @return 删除结果
     */
    @DeleteMapping(value = "/{id}")
    public Result<String> delete(@PathVariable Long id) {
        // 调用Service实现删除
        contentService.removeById(id);
        return new Result<String>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 修改Content数据
     * @param content 修改的数据
     * @param id 主键ID
     * @return 修改结果
     */
    @PutMapping(value = "/{id}")
    public Result<String> update(@RequestBody Content content, @PathVariable Long id) {
        // 设置主键值
        content.setId(id);
        // 调用Service实现修改
        contentService.updateById(content);
        return new Result<String>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 新增Content数据
     * @param content 新增的数据
     * @return 添加结果
     */
    @PostMapping
    public Result<String> add(@RequestBody Content content) {
        // 调用Service实现添加
        contentService.save(content);
        return new Result<String>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 根据ID查询Content数据
     * @param id 主键ID
     * @return 查询结果
     */
    @GetMapping("/{id}")
    public Result<Content> findById(@PathVariable Long id) {
        // 调用Service实现根据主键查询
        Content content = contentService.getById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", content);
    }

    /**
     * 查询Content全部数据
     * @return 查询结果
     */
    @GetMapping
    public Result<List<Content>> findAll() {
        // 调用Service实现查询所有
        List<Content> list = contentService.list();
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 根据分类的ID获取该分类下的所有的广告的列表
     * @param id 分类ID
     * @return 广告列表
     */
    @GetMapping(value = "/list/category/{id}")
    public Result<List<Content>> findByCategory(@PathVariable(name = "id") Long id) {
        // 构建查询条件
        LambdaQueryWrapper<Content> wrapper = new LambdaQueryWrapper<Content>().eq(Content::getCategoryId, id).eq(Content::getStatus, "1");

        // 执行查询
        List<Content> contents = contentService.list(wrapper);
        return new Result<>(true, StatusCode.OK, "查询成功 ", contents);
    }
}