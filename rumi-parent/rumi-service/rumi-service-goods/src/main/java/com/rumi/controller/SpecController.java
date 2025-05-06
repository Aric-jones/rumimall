package com.rumi.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.pojo.Spec;
import com.rumi.service.ISpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/6 22:03
 * @Description: Spec控制层
 */
@RestController
@RequestMapping("/spec")
public class SpecController {
    @Autowired
    private ISpecService specService;


    /**
     * @param spec
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.pojo.Spec>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:05
     * @Description: Spec分页条件搜索实现
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<IPage<Spec>> findPage(@RequestBody(required = false) Spec spec, @PathVariable int page, @PathVariable int size) {
        IPage<Spec> pageInfo = specService.findPage(spec, page, size);
        return new Result<IPage<Spec>>(true, StatusCode.OK, "查询成功", pageInfo);
    }


    /**
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.pojo.Spec>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:05
     * @Description: Spec分页条件搜索实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<IPage<Spec>> findPage(@PathVariable int page, @PathVariable int size) {
        IPage<Spec> pageInfo = specService.findPage(page, size);
        return new Result<IPage<Spec>>(true, StatusCode.OK, "查询成功", pageInfo);
    }


    /**
     * @param spec
     * @return com.rumi.common.entity.Result<java.util.List < com.rumi.pojo.Spec>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:12
     * @Description: 多条件搜索规格数据
     */
    @PostMapping(value = "/search")
    public Result<List<Spec>> findList(@RequestBody(required = false) Spec spec) {
        List<Spec> list = specService.findList(spec);
        return new Result<List<Spec>>(true, StatusCode.OK, "查询成功", list);
    }


    /**
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:14
     * @Description: 根据id删除
     */
    @DeleteMapping(value = "/{id}")
    public Result<String> delete(@PathVariable Integer id) {
        specService.deleteById(id);
        return new Result<String>(true, StatusCode.OK, "删除成功");
    }


    /**
     * @param spec
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:22
     * @Description: 修改
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Spec spec, @PathVariable Integer id) {
        spec.setId(id);
        boolean update = specService.updateById(spec);
        if (!update) {
            return new Result(false, StatusCode.ERROR, "修改失败");
        }
        return new Result(true, StatusCode.OK, "修改成功");
    }


    /**
     * @param spec
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:23
     * @Description: 添加
     */
    @PostMapping
    public Result add(@RequestBody Spec spec) {
        specService.add(spec);
        return new Result(true, StatusCode.OK, "添加成功");
    }


    /**
     * @param id
     * @return com.rumi.common.entity.Result<com.rumi.pojo.Spec>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:25
     * @Description: 根据id查询
     */
    @GetMapping("/{id}")
    public Result<Spec> findById(@PathVariable Integer id) {
        //根据ID查询
        Spec spec = specService.getById(id);
        return new Result<Spec>(true, StatusCode.OK, "查询成功", spec);
    }


    /**
     * @return com.rumi.common.entity.Result<com.rumi.pojo.Spec>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:25
     * @Description: 查询全部数据
     */
    @GetMapping
    public Result<List<Spec>> findAll() {
        List<Spec> list = specService.list();
        return new Result<List<Spec>>(true, StatusCode.OK, "查询成功", list);
    }
}
