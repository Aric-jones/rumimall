package com.rumi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rumi.pojo.Spu;

import java.util.List;

public interface ISpuService extends IService<Spu> {
    /**
     * 分页条件查询
     */
    IPage<Spu> findPage(Spu spu, int page, int size);

    /**
     * 简单分页查询
     */
    IPage<Spu> findPage(int page, int size);

    /**
     * 条件查询列表
     */
    List<Spu> findList(Spu spu);
}