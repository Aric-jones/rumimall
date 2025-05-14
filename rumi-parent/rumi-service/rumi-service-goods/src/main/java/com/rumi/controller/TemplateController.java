package com.rumi.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.goods.pojo.Template;
import com.rumi.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/6 21:47
 * @Description: Template管理控制层
 */
@RestController
@RequestMapping("/template")
public class TemplateController {
    @Autowired
    private ITemplateService templateService;

    /**
     * @param template
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.pojo.Template>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:50
     * @Description: Template分页条件搜索实现
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<IPage<Template>> findPage(@RequestBody(required = false) Template template, @PathVariable int page, @PathVariable int size) {
        IPage<Template> pageInfo = templateService.findPage(template, page, size);
        return new Result<IPage<Template>>(true, StatusCode.OK, "查询成功", pageInfo);
    }


    /**
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<PageInfo>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:53
     * @Description: Template分页搜索实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<IPage<Template>> findPage(@PathVariable int page, @PathVariable int size) {
        IPage<Template> pageInfo = templateService.findPage(page, size);
        return new Result<IPage<Template>>(true, StatusCode.OK, "查询成功", pageInfo);
    }


    /**
     * @param template
     * @return com.rumi.common.entity.Result<java.util.List < com.rumi.pojo.Template>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:55
     * @Description: 多条件搜索品牌数据
     */
    @PostMapping(value = "/search")
    public Result<List<Template>> findList(@RequestBody(required = false) Template template) {
        List<Template> list = templateService.findList(template);
        return new Result<List<Template>>(true, StatusCode.OK, "查询成功", list);
    }


    /**
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:56
     * @Description: 根据id删除
     */
    @DeleteMapping(value = "/{id}")
    public Result<String> delete(@PathVariable Integer id) {
        boolean remove = templateService.removeById(id);
        if (!remove) {
            return new Result<String>(false, StatusCode.ERROR, "删除失败");
        }
        return new Result<String>(true, StatusCode.OK, "删除成功");
    }


    /**
     * @param template
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:58
     * @Description: 修改
     */
    @PutMapping(value = "/{id}")
    public Result<String> update(@RequestBody Template template, @PathVariable Integer id) {
        template.setId(id);
        boolean update = templateService.updateById(template);
        if (!update) {
            return new Result<String>(false, StatusCode.ERROR, "修改失败");
        }
        return new Result<String>(true, StatusCode.OK, "修改成功");
    }


    /**
     * @param template
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:59
     * @Description: 新增
     */
    @PostMapping
    public Result<String> add(@RequestBody Template template) {
        boolean save = templateService.save(template);
        if (!save) {
            return new Result<String>(false, StatusCode.ERROR, "添加失败");
        }
        return new Result<String>(true, StatusCode.OK, "添加成功");
    }


    /**
     * @param id
     * @return com.rumi.common.entity.Result<com.rumi.pojo.Template>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:59
     * @Description: 根据id查询
     */
    @GetMapping("/{id}")
    public Result<Template> findById(@PathVariable Integer id) {
        Template template = templateService.getById(id);
        return new Result<Template>(true, StatusCode.OK, "查询成功", template);
    }

    /**
     * @return com.rumi.common.entity.Result<com.rumi.pojo.Template>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:00
     * @Description: 查询Template全部数据
     */
    @GetMapping
    public Result<List<Template>> findAll() {
        List<Template> list = templateService.list();
        return new Result<List<Template>>(true, StatusCode.OK, "查询成功", list);
    }
}
