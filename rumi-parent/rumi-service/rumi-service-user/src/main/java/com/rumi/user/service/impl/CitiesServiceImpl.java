package com.rumi.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rumi.user.dao.CitiesMapper;
import com.rumi.user.pojo.Cities;
import com.rumi.user.service.ICitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitiesServiceImpl extends ServiceImpl<CitiesMapper, Cities> implements ICitiesService {

    @Autowired
    private CitiesMapper citiesMapper;

    @Override
    public IPage<Cities> findPage(Cities cities, int page, int size) {
        QueryWrapper<Cities> queryWrapper = new QueryWrapper<>();

        if (cities != null) {
            if (cities.getCityid() != null && !cities.getCityid().isEmpty()) {
                queryWrapper.eq("cityid", cities.getCityid());
            }
            if (cities.getCity() != null && !cities.getCity().isEmpty()) {
                queryWrapper.eq("city", cities.getCity());
            }
            if (cities.getProvinceid() != null && !cities.getProvinceid().isEmpty()) {
                queryWrapper.eq("provinceid", cities.getProvinceid());
            }
        }

        IPage<Cities> iPage = new Page<>(page, size);
        return this.page(iPage, queryWrapper);
    }

    @Override
    public IPage<Cities> findPage(int page, int size) {
        IPage<Cities> iPage = new Page<>(page, size);
        return this.page(iPage);
    }
}