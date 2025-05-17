package com.rumi.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rumi.user.pojo.Provinces;

import java.util.List;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/17 22:38
 * @Description: Provinces业务层接口
 */
public interface IProvincesService extends IService<Provinces> {
    IPage<Provinces> findPage(Provinces provinces, int page, int size);
    IPage<Provinces> findPage(int page, int size);

    List<Provinces> findList(Provinces provinces);
    // 其他方法由 MyBatis-Plus 的 IService 提供
}