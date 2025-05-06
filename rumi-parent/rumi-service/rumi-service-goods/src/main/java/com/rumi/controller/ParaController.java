package com.rumi.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.pojo.Para;
import com.rumi.service.IParaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/6 22:29
 * @Description: para控制器
 */
@RestController
@RequestMapping("/para")
public class ParaController {
    
    @Autowired
    private IParaService paraService;
    
    /**
     * @param para
     * @param page
     * @param size
     * @return Result<PageInfo>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:33
     * @Description: Para分页条件搜索实现
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<IPage<Para>> findPage(@RequestBody(required = false) Para para, @PathVariable int page, @PathVariable  int size){
        //执行搜索
        IPage<Para> pageInfo = paraService.findPage(para, page, size);
        return new Result<IPage<Para>>(true, StatusCode.OK,"查询成功",pageInfo);
    }


    /**
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<PageInfo>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:36
     * @Description: Para分页搜索实现
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<IPage<Para>> findPage(@PathVariable  int page, @PathVariable  int size){
        //分页查询
        IPage<Para> pageInfo = paraService.findPage(page, size);
        return new Result<IPage<Para>>(true,StatusCode.OK,"查询成功",pageInfo);
    }


    /**
     * @param para
     * @return com.rumi.common.entity.Result<java.util.List < com.rumi.pojo.Para>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:37
     * @Description: 多条件搜索参数数据
     */
    @PostMapping(value = "/search" )
    public Result<List<Para>> findList(@RequestBody(required = false)  Para para){
        List<Para> list = paraService.findList(para);
        return new Result<List<Para>>(true,StatusCode.OK,"查询成功",list);
    }


    /**
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:41
     * @Description: 删除
     */
    @DeleteMapping(value = "/{id}" )
    public Result<String> delete(@PathVariable Integer id){
        paraService.deleteById(id);
        return new Result<String>(true,StatusCode.OK,"删除成功");
    }


    /**
     * @param para
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:43
     * @Description: 修改
     */
    @PutMapping(value="/{id}")
    public Result<String> update(@RequestBody  Para para,@PathVariable Integer id){
        para.setId(id);
        paraService.updateById(para);
        return new Result<String>(true,StatusCode.OK,"修改成功");
    }


    /**
     * @param para
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:44
     * @Description: 添加
     */
    @PostMapping
    public Result<String> add(@RequestBody   Para para){
        paraService.add(para);
        return new Result<String>(true,StatusCode.OK,"添加成功");
    }


    /**
     * @param id
     * @return com.rumi.common.entity.Result<com.rumi.pojo.Para>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:45
     * @Description: 根据id查询
     */
    @GetMapping("/{id}")
    public Result<Para> findById(@PathVariable Integer id){
        //根据ID查询
        Para para = paraService.getById(id);
        return new Result<Para>(true,StatusCode.OK,"查询成功",para);
    }


    /**
     * @return com.rumi.common.entity.Result<com.rumi.pojo.Para>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:45
     * @Description: 查询全部信息
     */
    @GetMapping
    public Result<List<Para>> findAll(){
        List<Para> list = paraService.list();
        return new Result<List<Para>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
