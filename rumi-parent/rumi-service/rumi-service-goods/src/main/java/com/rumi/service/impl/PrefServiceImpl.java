package com.rumi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rumi.dao.PrefMapper;
import com.rumi.goods.pojo.Pref;
import com.rumi.service.IPrefService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PrefServiceImpl extends ServiceImpl<PrefMapper, Pref> implements IPrefService {

    @Override
    public IPage<Pref> findPage(Pref pref, int page, int size) {
        LambdaQueryWrapper<Pref> wrapper = buildQueryWrapper(pref);
        Page<Pref> pageParam = new Page<>(page, size);
        return this.page(pageParam, wrapper);
    }

    @Override
    public IPage<Pref> findPage(int page, int size) {
        Page<Pref> pageParam = new Page<>(page, size);
        return this.page(pageParam);
    }

    @Override
    public List<Pref> findList(Pref pref) {
        LambdaQueryWrapper<Pref> wrapper = buildQueryWrapper(pref);
        return this.list(wrapper);
    }

    /**
     * 构建查询条件
     */
    private LambdaQueryWrapper<Pref> buildQueryWrapper(Pref pref) {
        return new LambdaQueryWrapper<Pref>()
                .eq(Objects.nonNull(pref.getId()), Pref::getId, pref.getId())
                .eq(Objects.nonNull(pref.getCateId()), Pref::getCateId, pref.getCateId())
                .eq(Objects.nonNull(pref.getBuyMoney()), Pref::getBuyMoney, pref.getBuyMoney())
                .eq(Objects.nonNull(pref.getPreMoney()), Pref::getPreMoney, pref.getPreMoney())
                .ge(Objects.nonNull(pref.getStartTime()), Pref::getStartTime, pref.getStartTime())
                .le(Objects.nonNull(pref.getEndTime()), Pref::getEndTime, pref.getEndTime())
                .eq(Objects.nonNull(pref.getType()), Pref::getType, pref.getType())
                .eq(Objects.nonNull(pref.getState()), Pref::getState, pref.getState());
    }
}