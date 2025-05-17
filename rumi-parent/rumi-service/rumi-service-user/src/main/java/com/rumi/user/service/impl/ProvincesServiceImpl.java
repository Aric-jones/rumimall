package com.rumi.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rumi.user.dao.ProvincesMapper;
import com.rumi.user.pojo.Provinces;
import com.rumi.user.service.IProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProvincesServiceImpl extends ServiceImpl<ProvincesMapper, Provinces> implements IProvincesService {

    @Autowired
    private ProvincesMapper provincesMapper;

    @Override
    public IPage<Provinces> findPage(Provinces provinces, int page, int size) {
        QueryWrapper<Provinces> queryWrapper = new QueryWrapper<>();

        if (provinces != null) {
            if (provinces.getProvinceid() != null && !provinces.getProvinceid().isEmpty()) {
                queryWrapper.eq("provinceid", provinces.getProvinceid());
            }
            if (provinces.getProvince() != null && !provinces.getProvince().isEmpty()) {
                queryWrapper.eq("province", provinces.getProvince());
            }
        }

        IPage<Provinces> iPage = new Page<>(page, size);
        return this.page(iPage, queryWrapper);
    }

    @Override
    public IPage<Provinces> findPage(int page, int size) {
        IPage<Provinces> iPage = new Page<>(page, size);
        return this.page(iPage);
    }

    @Override
    public List<Provinces> findList(Provinces provinces) {
        QueryWrapper<Provinces> queryWrapper = new QueryWrapper<>();

        if (provinces != null) {
            if (provinces.getProvinceid() != null && !provinces.getProvinceid().isEmpty()) {
                queryWrapper.eq("provinceid", provinces.getProvinceid());
            }
            if (provinces.getProvince() != null && !provinces.getProvince().isEmpty()) {
                queryWrapper.eq("province", provinces.getProvince());
            }
        }
        return provincesMapper.selectList(queryWrapper);

    }
}