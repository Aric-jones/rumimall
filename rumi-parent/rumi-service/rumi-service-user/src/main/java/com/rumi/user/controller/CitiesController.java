package com.rumi.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.user.pojo.Cities;
import com.rumi.user.service.ICitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/17 22:38
 * @Description: Cities管理控制层
 */
@RestController
@RequestMapping("/cities")
@CrossOrigin
public class CitiesController {
    @Autowired
    private ICitiesService citiesService;

    /**
     * @param cities
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.user.pojo.Cities>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: Cities分页条件搜索实现
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<IPage<Cities>> findPage(@RequestBody(required = false) Cities cities, @PathVariable int page, @PathVariable int size) {
        IPage<Cities> pageInfo = citiesService.findPage(cities, page, size);
        return new Result<IPage<Cities>>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.user.pojo.Cities>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: Cities分页搜索实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<IPage<Cities>> findPage(@PathVariable int page, @PathVariable int size) {
        IPage<Cities> pageInfo = citiesService.findPage(page, size);
        return new Result<IPage<Cities>>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * @param cities
     * @return com.rumi.common.entity.Result<java.util.List < com.rumi.user.pojo.Cities>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 多条件搜索
     */
    @PostMapping(value = "/search")
    public Result<List<Cities>> findList(@RequestBody(required = false) Cities cities) {
        List<Cities> list = citiesService.findList(cities);
        return new Result<List<Cities>>(true, StatusCode.OK, "查询成功", list);
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
        boolean remove = citiesService.removeById(id);
        if (!remove) {
            return new Result<String>(false, StatusCode.ERROR, "删除失败");
        }
        return new Result<String>(true, StatusCode.OK, "删除成功");
    }

    /**
     * @param cities
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 修改
     */
    @PutMapping(value = "/{id}")
    public Result<String> update(@RequestBody Cities cities, @PathVariable String id) {
        cities.setCityid(id);
        boolean update = citiesService.updateById(cities);
        if (!update) {
            return new Result<String>(false, StatusCode.ERROR, "修改失败");
        }
        return new Result<String>(true, StatusCode.OK, "修改成功");
    }

    /**
     * @param cities
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 添加
     */
    @PostMapping
    public Result<String> add(@RequestBody Cities cities) {
        boolean save = citiesService.save(cities);
        if (!save) {
            return new Result<String>(false, StatusCode.ERROR, "添加失败");
        }
        return new Result<String>(true, StatusCode.OK, "添加成功");
    }

    /**
     * @param id
     * @return com.rumi.common.entity.Result<com.rumi.user.pojo.Cities>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 根据id查询
     */
    @GetMapping("/{id}")
    public Result<Cities> findById(@PathVariable String id) {
        Cities cities = citiesService.getById(id);
        return new Result<Cities>(true, StatusCode.OK, "查询成功", cities);
    }

    /**
     * @return com.rumi.common.entity.Result<com.rumi.user.pojo.Cities>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 查询Cities全部信息
     */
    @GetMapping
    public Result<List<Cities>> findAll() {
        List<Cities> list = citiesService.list();
        return new Result<List<Cities>>(true, StatusCode.OK, "查询成功", list);
    }
}