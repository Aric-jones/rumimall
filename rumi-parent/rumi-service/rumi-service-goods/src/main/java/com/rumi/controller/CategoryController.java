package com.rumi.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.pojo.Category;
import com.rumi.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CSH
 * @since 2025-05-06
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private ICategoryService categoryService;
    
    
    /**
     * @param category
     * @param page
     * @param size
     * @return Result<PageInfo>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 23:01
     * @Description: Category分页条件搜索实现
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<IPage<Category>> findPage(@RequestBody(required = false)  Category category, @PathVariable  int page, @PathVariable int size){
        IPage<Category> pageInfo = categoryService.findPage(category, page, size);
        return new Result<IPage<Category>>(true, StatusCode.OK,"查询成功",pageInfo);
    }


    /**
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<PageInfo>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 23:03
     * @Description: Category分页搜索实现
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<IPage<Category>> findPage(@PathVariable  int page, @PathVariable  int size){
        IPage<Category> pageInfo = categoryService.findPage(page, size);
        return new Result<IPage<Category>>(true,StatusCode.OK,"查询成功",pageInfo);
    }


    /**
     * @param category
     * @return com.rumi.common.entity.Result<java.util.List < com.rumi.pojo.Category>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 23:04
     * @Description: 多条件搜索分类数据
     */
    @PostMapping(value = "/search" )
    public Result<List<Category>> findList(@RequestBody(required = false)  Category category){
        List<Category> list = categoryService.findList(category);
        return new Result<List<Category>>(true,StatusCode.OK,"查询成功",list);
    }


    /**
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 23:06
     * @Description: 根据id删除
     */
    @DeleteMapping(value = "/{id}" )
    public Result<String> delete(@PathVariable Integer id){
        boolean remove = categoryService.removeById(id);
        if (!remove) {
            return new Result<String>(false,StatusCode.ERROR,"删除失败");
        }
        return new Result<String>(true,StatusCode.OK,"删除成功");
    }


    /**
     * @param category
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 23:07
     * @Description: 修改
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Category category,@PathVariable Integer id){
        category.setId(id);
        categoryService.updateById(category);
        return new Result(true,StatusCode.OK,"修改成功");
    }


    /**
     * @param category
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 23:08
     * @Description: 添加
     */
    @PostMapping
    public Result add(@RequestBody Category category){
        categoryService.save(category);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /**
     * @param id
     * @return com.rumi.common.entity.Result<com.rumi.pojo.Category>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 23:08
     * @Description: 根据di查询
     */
    @GetMapping("/{id}")
    public Result<Category> findById(@PathVariable Integer id){
        Category category = categoryService.getById(id);
        return new Result<Category>(true,StatusCode.OK,"查询成功",category);
    }

    /**
     * @return com.rumi.common.entity.Result<com.rumi.pojo.Category>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 23:09
     * @Description: 查询全部
     */
    @GetMapping
    public Result<List<Category>> findAll(){
        List<Category> list = categoryService.list();
        return new Result<List<Category>>(true, StatusCode.OK,"查询成功",list) ;
    }


    /**
     * @param pid
     * @return com.rumi.common.entity.Result<com.rumi.pojo.Category>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 23:09
     * @Description: 根据父id查询
     */
    @RequestMapping(value ="/list/{pid}")
    public Result<List<Category>> findByPrantId(@PathVariable(value = "pid")Integer pid){
        //根据父节点ID查询
        List<Category> list = categoryService.findByParentId(pid);
        return new Result<List<Category>>(true,StatusCode.OK,"查询成功",list);
    }

}
