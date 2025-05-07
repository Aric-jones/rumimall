package com.rumi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rumi.pojo.Pref;

import java.util.List;

public interface IPrefService extends IService<Pref> {
    /**
     * 分页条件查询
     */
    IPage<Pref> findPage(Pref pref, int page, int size);

    /**
     * 简单分页查询
     */
    IPage<Pref> findPage(int page, int size);

    /**
     * 条件查询列表
     */
    List<Pref> findList(Pref pref);
}