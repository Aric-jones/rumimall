package com.rumi.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rumi.user.dao.UndoLogMapper;
import com.rumi.user.pojo.UndoLog;
import com.rumi.user.service.IUndoLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UndoLogServiceImpl extends ServiceImpl<UndoLogMapper, UndoLog> implements IUndoLogService {

    @Autowired
    private UndoLogMapper undoLogMapper;

    @Override
    public IPage<UndoLog> findPage(UndoLog undoLog, int page, int size) {
        QueryWrapper<UndoLog> queryWrapper = new QueryWrapper<>();

        if (undoLog != null) {
            if (undoLog.getId() != null) {
                queryWrapper.eq("id", undoLog.getId());
            }
            if (undoLog.getBranchId() != null) {
                queryWrapper.eq("branch_id", undoLog.getBranchId());
            }
            if (undoLog.getXid() != null) {
                queryWrapper.eq("xid", undoLog.getXid());
            }
            if (undoLog.getRollbackInfo() != null) {
                queryWrapper.eq("rollback_info", undoLog.getRollbackInfo());
            }
            if (undoLog.getLogStatus() != null) {
                queryWrapper.eq("log_status", undoLog.getLogStatus());
            }
            if (undoLog.getLogCreated() != null) {
                queryWrapper.eq("log_created", undoLog.getLogCreated());
            }
            if (undoLog.getLogModified() != null) {
                queryWrapper.eq("log_modified", undoLog.getLogModified());
            }
            if (undoLog.getExt() != null) {
                queryWrapper.eq("ext", undoLog.getExt());
            }
        }

        IPage<UndoLog> iPage = new Page<>(page, size);
        return this.page(iPage, queryWrapper);
    }

    @Override
    public IPage<UndoLog> findPage(int page, int size) {
        IPage<UndoLog> iPage = new Page<>(page, size);
        return this.page(iPage);
    }
}