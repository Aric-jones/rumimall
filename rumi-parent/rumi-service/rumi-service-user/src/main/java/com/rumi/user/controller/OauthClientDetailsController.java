package com.rumi.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.user.pojo.OauthClientDetails;
import com.rumi.user.service.IOauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/17 22:38
 * @Description: OauthClientDetails管理控制层
 */
@RestController
@RequestMapping("/oauthClientDetails")
@CrossOrigin
public class OauthClientDetailsController {
    @Autowired
    private IOauthClientDetailsService oauthClientDetailsService;

    /**
     * @param oauthClientDetails
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.user.pojo.OauthClientDetails>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: OauthClientDetails分页条件搜索实现
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<IPage<OauthClientDetails>> findPage(@RequestBody(required = false) OauthClientDetails oauthClientDetails, @PathVariable int page, @PathVariable int size) {
        IPage<OauthClientDetails> pageInfo = oauthClientDetailsService.findPage(oauthClientDetails, page, size);
        return new Result<IPage<OauthClientDetails>>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.user.pojo.OauthClientDetails>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: OauthClientDetails分页搜索实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<IPage<OauthClientDetails>> findPage(@PathVariable int page, @PathVariable int size) {
        IPage<OauthClientDetails> pageInfo = oauthClientDetailsService.findPage(page, size);
        return new Result<IPage<OauthClientDetails>>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * @param oauthClientDetails
     * @return com.rumi.common.entity.Result<java.util.List < com.rumi.user.pojo.OauthClientDetails>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 多条件搜索
     */
    @PostMapping(value = "/search")
    public Result<List<OauthClientDetails>> findList(@RequestBody(required = false) OauthClientDetails oauthClientDetails) {
        List<OauthClientDetails> list = oauthClientDetailsService.findList(oauthClientDetails);
        return new Result<List<OauthClientDetails>>(true, StatusCode.OK, "查询成功", list);
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
        boolean remove = oauthClientDetailsService.removeById(id);
        if (!remove) {
            return new Result<String>(false, StatusCode.ERROR, "删除失败");
        }
        return new Result<String>(true, StatusCode.OK, "删除成功");
    }

    /**
     * @param oauthClientDetails
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 修改
     */
    @PutMapping(value = "/{id}")
    public Result<String> update(@RequestBody OauthClientDetails oauthClientDetails, @PathVariable String id) {
        oauthClientDetails.setClientId(id);
        boolean update = oauthClientDetailsService.updateById(oauthClientDetails);
        if (!update) {
            return new Result<String>(false, StatusCode.ERROR, "修改失败");
        }
        return new Result<String>(true, StatusCode.OK, "修改成功");
    }

    /**
     * @param oauthClientDetails
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 添加
     */
    @PostMapping
    public Result<String> add(@RequestBody OauthClientDetails oauthClientDetails) {
        boolean save = oauthClientDetailsService.save(oauthClientDetails);
        if (!save) {
            return new Result<String>(false, StatusCode.ERROR, "添加失败");
        }
        return new Result<String>(true, StatusCode.OK, "添加成功");
    }

    /**
     * @param id
     * @return com.rumi.common.entity.Result<com.rumi.user.pojo.OauthClientDetails>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 根据id查询
     */
    @GetMapping("/{id}")
    public Result<OauthClientDetails> findById(@PathVariable String id) {
        OauthClientDetails oauthClientDetails = oauthClientDetailsService.getById(id);
        return new Result<OauthClientDetails>(true, StatusCode.OK, "查询成功", oauthClientDetails);
    }

    /**
     * @return com.rumi.common.entity.Result<com.rumi.user.pojo.OauthClientDetails>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 查询OauthClientDetails全部信息
     */
    @GetMapping
    public Result<List<OauthClientDetails>> findAll() {
        List<OauthClientDetails> list = oauthClientDetailsService.list();
        return new Result<List<OauthClientDetails>>(true, StatusCode.OK, "查询成功", list);
    }
}