package com.rumi.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.goods.pojo.Pref;
import com.rumi.service.IPrefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CSH
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/pref")
public class PrefController {
    @Autowired
    private IPrefService prefService;

    /**
     * 分页条件查询
     */
    @PostMapping("/search/{page}/{size}")
    public Result<IPage<Pref>> findPage(
            @RequestBody(required = false) Pref pref,
            @PathVariable int page,
            @PathVariable int size) {
        IPage<Pref> pageInfo = prefService.findPage(pref, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * 简单分页查询
     */
    @GetMapping("/search/{page}/{size}")
    public Result<IPage<Pref>> findPage(
            @PathVariable int page,
            @PathVariable int size) {
        IPage<Pref> pageInfo = prefService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * 条件查询列表
     */
    @PostMapping("/search")
    public Result<List<Pref>> findList(@RequestBody(required = false) Pref pref) {
        List<Pref> list = prefService.findList(pref);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    public Result<Pref> findById(@PathVariable Integer id) {
        Pref pref = prefService.getById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", pref);
    }

    /**
     * 添加优惠活动
     */
    @PostMapping
    public Result<String> add(@RequestBody Pref pref) {
        prefService.save(pref);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改优惠活动
     */
    @PutMapping("/{id}")
    public Result<String> update(@RequestBody Pref pref, @PathVariable Integer id) {
        pref.setId(id);
        boolean update = prefService.updateById(pref);
        if (!update) {
            return new Result<>(false, StatusCode.ERROR, "修改失败");
        }
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除优惠活动
     */
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Integer id) {
        prefService.removeById(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 查询所有有效优惠活动
     */
    @GetMapping("/active")
    public Result<List<Pref>> findActivePrefs() {
        Pref query = new Pref();
        query.setState("1"); // 只查询状态为有效的
        List<Pref> list = prefService.findList(query);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }
}
