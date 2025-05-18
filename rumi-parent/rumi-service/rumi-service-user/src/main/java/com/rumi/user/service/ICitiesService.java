package com.rumi.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rumi.user.pojo.Cities;

import java.util.List;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/17 22:38
 * @Description: Cities业务层接口
 */
public interface ICitiesService extends IService<Cities> {
    IPage<Cities> findPage(Cities cities, int page, int size);
    IPage<Cities> findPage(int page, int size);

    List<Cities> findList(Cities cities);
    // 其他方法由 MyBatis-Plus 的 IService 提供
}