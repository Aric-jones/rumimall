package com.rumi.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.user.pojo.UndoLog;
import com.rumi.user.service.IUndoLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/17 22:38
 * @Description: UndoLog管理控制层
 */
@RestController
@RequestMapping("/undoLog")
@CrossOrigin
public class UndoLogController {
    @Autowired
    private IUndoLogService undoLogService;

    /**
     * @param undoLog
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.user.pojo.UndoLog>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: UndoLog分页条件搜索实现
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<IPage<UndoLog>> findPage(@RequestBody(required = false) UndoLog undoLog, @PathVariable int page, @PathVariable int size) {
        IPage<UndoLog> pageInfo = undoLogService.findPage(undoLog, page, size);
        return new Result<IPage<UndoLog>>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * @param page
     * @param size
     * @return com.rumi.common.entity.Result<com.baomidou.mybatisplus.core.metadata.IPage < com.rumi.user.pojo.UndoLog>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: UndoLog分页搜索实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<IPage<UndoLog>> findPage(@PathVariable int page, @PathVariable int size) {
        IPage<UndoLog> pageInfo = undoLogService.findPage(page, size);
        return new Result<IPage<UndoLog>>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * @param undoLog
     * @return com.rumi.common.entity.Result<java.util.List < com.rumi.user.pojo.UndoLog>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 多条件搜索
     */
    @PostMapping(value = "/search")
    public Result<List<UndoLog>> findList(@RequestBody(required = false) UndoLog undoLog) {
        List<UndoLog> list = undoLogService.findList(undoLog);
        return new Result<List<UndoLog>>(true, StatusCode.OK, "查询成功", list);
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
    public Result<String> delete(@PathVariable Long id) {
        boolean remove = undoLogService.removeById(id);
        if (!remove) {
            return new Result<String>(false, StatusCode.ERROR, "删除失败");
        }
        return new Result<String>(true, StatusCode.OK, "删除成功");
    }

    /**
     * @param undoLog
     * @param id
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 修改
     */
    @PutMapping(value = "/{id}")
    public Result<String> update(@RequestBody UndoLog undoLog, @PathVariable Long id) {
        undoLog.setId(id);
        boolean update = undoLogService.updateById(undoLog);
        if (!update) {
            return new Result<String>(false, StatusCode.ERROR, "修改失败");
        }
        return new Result<String>(true, StatusCode.OK, "修改成功");
    }

    /**
     * @param undoLog
     * @return com.rumi.common.entity.Result
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 添加
     */
    @PostMapping
    public Result<String> add(@RequestBody UndoLog undoLog) {
        boolean save = undoLogService.save(undoLog);
        if (!save) {
            return new Result<String>(false, StatusCode.ERROR, "添加失败");
        }
        return new Result<String>(true, StatusCode.OK, "添加成功");
    }

    /**
     * @param id
     * @return com.rumi.common.entity.Result<com.rumi.user.pojo.UndoLog>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 根据id查询
     */
    @GetMapping("/{id}")
    public Result<UndoLog> findById(@PathVariable Long id) {
        UndoLog undoLog = undoLogService.getById(id);
        return new Result<UndoLog>(true, StatusCode.OK, "查询成功", undoLog);
    }

    /**
     * @return com.rumi.common.entity.Result<com.rumi.user.pojo.UndoLog>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/17 22:38
     * @Description: 查询UndoLog全部信息
     */
    @GetMapping
    public Result<List<UndoLog>> findAll() {
        List<UndoLog> list = undoLogService.list();
        return new Result<List<UndoLog>>(true, StatusCode.OK, "查询成功", list);
    }
}