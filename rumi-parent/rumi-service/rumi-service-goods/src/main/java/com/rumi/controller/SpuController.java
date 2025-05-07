package com.rumi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.pojo.Spu;
import com.rumi.service.ISpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spu")
public class SpuController {
    @Autowired
    private ISpuService spuService;

    /**
     * 分页条件查询
     */
    @PostMapping("/search/{page}/{size}")
    public Result<IPage<Spu>> findPage(
            @RequestBody(required = false) Spu spu,
            @PathVariable int page,
            @PathVariable int size) {
        IPage<Spu> pageInfo = spuService.findPage(spu, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * 简单分页查询
     */
    @GetMapping("/search/{page}/{size}")
    public Result<IPage<Spu>> findPage(
            @PathVariable int page,
            @PathVariable int size) {
        IPage<Spu> pageInfo = spuService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * 条件查询列表
     */
    @PostMapping("/search")
    public Result<List<Spu>> findList(@RequestBody(required = false) Spu spu) {
        List<Spu> list = spuService.findList(spu);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    public Result<Spu> findById(@PathVariable Long id) {
        Spu spu = spuService.getById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", spu);
    }

    /**
     * 添加SPU
     */
    @PostMapping
    public Result<String> add(@RequestBody Spu spu) {
        spuService.save(spu);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改SPU
     */
    @PutMapping("/{id}")
    public Result<String> update(@RequestBody Spu spu, @PathVariable Long id) {
        spu.setId(id);
        boolean update = spuService.updateById(spu);
        if (!update) {
            return new Result<>(false, StatusCode.ERROR, "修改失败");
        }
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除SPU
     */
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        spuService.removeById(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 上架/下架操作
     */
    @PutMapping("/{id}/marketable/{isMarketable}")
    public Result<String> updateMarketable(
            @PathVariable Long id,
            @PathVariable String isMarketable) {
        Spu spu = new Spu();
        spu.setId(id);
        spu.setIsMarketable(isMarketable);
        spuService.updateById(spu);
        return new Result<>(true, StatusCode.OK, "状态更新成功");
    }

    /**
     * 启用/禁用规格
     */
    @PutMapping("/{id}/enable-spec/{isEnableSpec}")
    public Result<String> updateEnableSpec(
            @PathVariable Long id,
            @PathVariable String isEnableSpec) {
        Spu spu = new Spu();
        spu.setId(id);
        spu.setIsEnableSpec(isEnableSpec);
        spuService.updateById(spu);
        return new Result<>(true, StatusCode.OK, "规格设置更新成功");
    }
}