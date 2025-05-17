package com.rumi.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.user.pojo.Provinces;
import com.rumi.user.service.IProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/17 22:38
 * @Description: Provinces管理控制层
 */
@RestController
@RequestMapping("/provinces")
@CrossOrigin
public class ProvincesController {
    @Autowired
    private IProvincesService provincesService;

    /**
     * @param provinces
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.user.pojo.Provinces>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: Provinces分页条件搜索实现
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<IPage<Provinces>> findPage(@RequestBody(required = false) Provinces provinces, @PathVariable int page, @PathVariable int size) {
        IPage<Provinces> pageInfo = provincesService.findPage(provinces, page, size);
        return new Result<IPage<Provinces>>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.user.pojo.Provinces>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: Provinces分页搜索实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<IPage<Provinces>> findPage(@PathVariable int page, @PathVariable int size) {
        IPage<Provinces> pageInfo = provincesService.findPage(page, size);
        return new Result<IPage<Provinces>>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * @param provinces
     * @return com.rumi.common.entity.Result<java.util.List < com.rumi.user.pojo.Provinces>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 多条件搜索
     */
    @PostMapping(value = "/search")
    public Result<List<Provinces>> findList(@RequestBody(required = false) Provinces provinces) {
        List<Provinces> list = provincesService.findList(provinces);
        return new Result<List<Provinces>>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 根据id删除
     */
    @DeleteMapping(value = "/{id}")
    public Result<String> delete(@PathVariable String id) {
        boolean remove = provincesService.removeById(id);
        if (!remove) {
            return new Result<String>(false, StatusCode.ERROR, "删除失败");
        }
        return new Result<String>(true, StatusCode.OK, "删除成功");
    }

    /**
     * @param provinces
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 修改
     */
    @PutMapping(value = "/{id}")
    public Result<String> update(@RequestBody Provinces provinces, @PathVariable String id) {
        provinces.setProvinceid(id);
        boolean update = provincesService.updateById(provinces);
        if (!update) {
            return new Result<String>(false, StatusCode.ERROR, "修改失败");
        }
        return new Result<String>(true, StatusCode.OK, "修改成功");
    }

    /**
     * @param provinces
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 添加
     */
    @PostMapping
    public Result<String> add(@RequestBody Provinces provinces) {
        boolean save = provincesService.save(provinces);
        if (!save) {
            return new Result<String>(false, StatusCode.ERROR, "添加失败");
        }
        return new Result<String>(true, StatusCode.OK, "添加成功");
    }

    /**
     * @param id
     * @return com.rumi.common.entity.Result<com.rumi.user.pojo.Provinces>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 根据id查询
     */
    @GetMapping("/{id}")
    public Result<Provinces> findById(@PathVariable String id) {
        Provinces provinces = provincesService.getById(id);
        return new Result<Provinces>(true, StatusCode.OK, "查询成功", provinces);
    }

    /**
     * @return com.rumi.common.entity.Result<com.rumi.user.pojo.Provinces>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 查询Provinces全部信息
     */
    @GetMapping
    public Result<List<Provinces>> findAll() {
        List<Provinces> list = provincesService.list();
        return new Result<List<Provinces>>(true, StatusCode.OK, "查询成功", list);
    }
}