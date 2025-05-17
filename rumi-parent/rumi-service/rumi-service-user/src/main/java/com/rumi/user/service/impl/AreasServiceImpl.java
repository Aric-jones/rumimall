package com.rumi.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rumi.user.dao.AreasMapper;
import com.rumi.user.pojo.Areas;
import com.rumi.user.service.IAreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreasServiceImpl extends ServiceImpl<AreasMapper, Areas> implements IAreasService {

    @Autowired
    private AreasMapper areasMapper;

    @Override
    public IPage<Areas> findPage(Areas areas, int page, int size) {
        QueryWrapper<Areas> queryWrapper = new QueryWrapper<>();

        if (areas != null) {
            if (areas.getAreaid() != null && !areas.getAreaid().isEmpty()) {
                queryWrapper.eq("areaid", areas.getAreaid());
            }
            if (areas.getArea() != null && !areas.getArea().isEmpty()) {
                queryWrapper.eq("area", areas.getArea());
            }
            if (areas.getCityid() != null && !areas.getCityid().isEmpty()) {
                queryWrapper.eq("cityid", areas.getCityid());
            }
        }

        IPage<Areas> iPage = new Page<>(page, size);
        return this.page(iPage, queryWrapper);
    }

    @Override
    public IPage<Areas> findPage(int page, int size) {
        IPage<Areas> iPage = new Page<>(page, size);
        return this.page(iPage);
    }
}