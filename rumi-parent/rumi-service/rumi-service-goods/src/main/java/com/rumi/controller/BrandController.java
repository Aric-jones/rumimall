package com.rumi.controller;


import com.rumi.pojo.Brand;
import com.rumi.service.IBrandService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author CSH
 * @since 2025-05-04
 */
@RestController
@RequestMapping("/brand")
@CrossOrigin
public class BrandController {
    @Autowired
    private IBrandService brandService;

    /**
     * @return entity.Result<java.util.List < com.rumi.pojo.Brand>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/4 21:13
     * @Description: 查询全部brand
     */
    @GetMapping
    public Result<List<Brand>> findAll() {
        List<Brand> list = brandService.list();
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * @param id
     * @return entity.Result<com.rumi.pojo.Brand>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/4 21:13
     * @Description: 根据id查询Brand
     */
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable(name = "id") Integer id){
        Brand brand = brandService.getById(id);
        return new Result<Brand>(true, StatusCode.OK, "查询成功", brand);
    }

    /**
     * @param brand
     * @return entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/4 21:16
     * @Description: 添加brand
     */
    @PostMapping
    public Result add(@RequestBody Brand brand){
        boolean add = brandService.save(brand);
        if (add){
            return new Result(true, StatusCode.OK, "添加成功");
        }else {
            return new Result(false, StatusCode.ERROR, "添加失败");
        }
    }

    /**
     * @param brand
     * @param id
     * @return entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/4 21:17
     * @Description: 更新brand
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Brand brand, @PathVariable(name = "id") Integer id){
        brandService.updateById(brand);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * @param id
     * @return entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/4 21:17
     * @Description: 根据id删除brand
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(name = "id") Integer id){
        brandService.removeById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * @param page
     * @param size
     * @return entity.Result<java.util.List < com.rumi.pojo.Brand>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/4 21:21
     * @Description: 分页查询
     */
    @GetMapping("/page/{page}/{size}")
    public Result<List<Brand>> findPage(@PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size){
        List<Brand> list = brandService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }



}
