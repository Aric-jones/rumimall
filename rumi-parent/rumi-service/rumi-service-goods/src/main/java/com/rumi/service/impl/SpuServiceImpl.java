package com.rumi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rumi.dao.SpuMapper;
import com.rumi.pojo.Spu;
import com.rumi.service.ISpuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements ISpuService {

    @Override
    public IPage<Spu> findPage(Spu spu, int page, int size) {
        LambdaQueryWrapper<Spu> wrapper = buildQueryWrapper(spu);
        Page<Spu> pageParam = new Page<>(page, size);
        return this.page(pageParam, wrapper);
    }

    @Override
    public IPage<Spu> findPage(int page, int size) {
        Page<Spu> pageParam = new Page<>(page, size);
        return this.page(pageParam);
    }

    @Override
    public List<Spu> findList(Spu spu) {
        LambdaQueryWrapper<Spu> wrapper = buildQueryWrapper(spu);
        return this.list(wrapper);
    }

    /**
     * 构建查询条件
     */
    private LambdaQueryWrapper<Spu> buildQueryWrapper(Spu spu) {
        return new LambdaQueryWrapper<Spu>()
                .eq(Objects.nonNull(spu.getId()), Spu::getId, spu.getId())
                .eq(Objects.nonNull(spu.getBrandId()), Spu::getBrandId, spu.getBrandId())
                .eq(Objects.nonNull(spu.getCategory1Id()), Spu::getCategory1Id, spu.getCategory1Id())
                .eq(Objects.nonNull(spu.getCategory2Id()), Spu::getCategory2Id, spu.getCategory2Id())
                .eq(Objects.nonNull(spu.getCategory3Id()), Spu::getCategory3Id, spu.getCategory3Id())
                .eq(Objects.nonNull(spu.getTemplateId()), Spu::getTemplateId, spu.getTemplateId())
                .like(Objects.nonNull(spu.getName()), Spu::getName, spu.getName())
                .like(Objects.nonNull(spu.getCaption()), Spu::getCaption, spu.getCaption())
                .eq(Objects.nonNull(spu.getIsMarketable()), Spu::getIsMarketable, spu.getIsMarketable())
                .eq(Objects.nonNull(spu.getIsEnableSpec()), Spu::getIsEnableSpec, spu.getIsEnableSpec())
                .eq(Objects.nonNull(spu.getStatus()), Spu::getStatus, spu.getStatus())
                .orderByDesc(Spu::getSaleNum);
    }
}