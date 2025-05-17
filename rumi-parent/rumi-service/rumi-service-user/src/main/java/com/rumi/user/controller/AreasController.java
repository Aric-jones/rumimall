package com.rumi.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.user.pojo.Areas;
import com.rumi.user.service.IAreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/17 22:38
 * @Description: Areas管理控制层
 */
@RestController
@RequestMapping("/areas")
@CrossOrigin
public class AreasController {
    @Autowired
    private IAreasService areasService;

    /**
     * @param areas
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.user.pojo.Areas>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: Areas分页条件搜索实现
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<IPage<Areas>> findPage(@RequestBody(required = false) Areas areas, @PathVariable int page, @PathVariable int size) {
        IPage<Areas> pageInfo = areasService.findPage(areas, page, size);
        return new Result<IPage<Areas>>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.user.pojo.Areas>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: Areas分页搜索实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<IPage<Areas>> findPage(@PathVariable int page, @PathVariable int size) {
        IPage<Areas> pageInfo = areasService.findPage(page, size);
        return new Result<IPage<Areas>>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * @param areas
     * @return com.rumi.common.entity.Result<java.util.List < com.rumi.user.pojo.Areas>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 多条件搜索
     */
    @PostMapping(value = "/search")
    public Result<List<Areas>> findList(@RequestBody(required = false) Areas areas) {
        List<Areas> list = areasService.findList(areas);
        return new Result<List<Areas>>(true, StatusCode.OK, "查询成功", list);
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
        boolean remove = areasService.removeById(id);
        if (!remove) {
            return new Result<String>(false, StatusCode.ERROR, "删除失败");
        }
        return new Result<String>(true, StatusCode.OK, "删除成功");
    }

    /**
     * @param areas
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 修改
     */
    @PutMapping(value = "/{id}")
    public Result<String> update(@RequestBody Areas areas, @PathVariable String id) {
        areas.setAreaid(id);
        boolean update = areasService.updateById(areas);
        if (!update) {
            return new Result<String>(false, StatusCode.ERROR, "修改失败");
        }
        return new Result<String>(true, StatusCode.OK, "修改成功");
    }

    /**
     * @param areas
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 添加
     */
    @PostMapping
    public Result<String> add(@RequestBody Areas areas) {
        boolean save = areasService.save(areas);
        if (!save) {
            return new Result<String>(false, StatusCode.ERROR, "添加失败");
        }
        return new Result<String>(true, StatusCode.OK, "添加成功");
    }

    /**
     * @param id
     * @return com.rumi.common.entity.Result<com.rumi.user.pojo.Areas>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 根据id查询
     */
    @GetMapping("/{id}")
    public Result<Areas> findById(@PathVariable String id) {
        Areas areas = areasService.getById(id);
        return new Result<Areas>(true, StatusCode.OK, "查询成功", areas);
    }

    /**
     * @return com.rumi.common.entity.Result<com.rumi.user.pojo.Areas>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 查询Areas全部信息
     */
    @GetMapping
    public Result<List<Areas>> findAll() {
        List<Areas> list = areasService.list();
        return new Result<List<Areas>>(true, StatusCode.OK, "查询成功", list);
    }
}