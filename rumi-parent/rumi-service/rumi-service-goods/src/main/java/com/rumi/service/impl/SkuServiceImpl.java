package com.rumi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rumi.dao.SkuMapper;
import com.rumi.goods.pojo.Sku;
import com.rumi.service.ISkuService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements ISkuService {

    @Override
    public IPage<Sku> findPage(Sku sku, int page, int size) {
        LambdaQueryWrapper<Sku> wrapper = buildQueryWrapper(sku);
        Page<Sku> pageParam = new Page<>(page, size);
        return this.page(pageParam, wrapper);
    }

    @Override
    public IPage<Sku> findPage(int page, int size) {
        Page<Sku> pageParam = new Page<>(page, size);
        return this.page(pageParam);
    }

    @Override
    public List<Sku> findList(Sku sku) {
        LambdaQueryWrapper<Sku> wrapper = buildQueryWrapper(sku);
        return this.list(wrapper);
    }

    /**
     * 根据状态查询
     *
     * @param status
     */
    @Override
    public List<Sku> findByStatus(String status) {
        LambdaQueryWrapper<Sku> wrapper = new LambdaQueryWrapper<Sku>()
                .eq(Sku::getStatus, status);
        return this.list(wrapper);
    }



    /**
     * 构建查询条件
     */
    private LambdaQueryWrapper<Sku> buildQueryWrapper(Sku sku) {
        return new LambdaQueryWrapper<Sku>()
                .eq(Objects.nonNull(sku.getId()), Sku::getId, sku.getId())
                .eq(Objects.nonNull(sku.getSpuId()), Sku::getSpuId, sku.getSpuId())
                .eq(Objects.nonNull(sku.getCategoryId()), Sku::getCategoryId, sku.getCategoryId())
                .like(Objects.nonNull(sku.getName()), Sku::getName, sku.getName())
                .eq(Objects.nonNull(sku.getStatus()), Sku::getStatus, sku.getStatus())
                .ge(Objects.nonNull(sku.getPrice()), Sku::getPrice, sku.getPrice())
                .le(Objects.nonNull(sku.getPrice()), Sku::getPrice, sku.getPrice())
                .ge(Objects.nonNull(sku.getNum()), Sku::getNum, sku.getNum())
                .le(Objects.nonNull(sku.getNum()), Sku::getNum, sku.getNum())
                .orderByDesc(Sku::getCreateTime);
    }
}