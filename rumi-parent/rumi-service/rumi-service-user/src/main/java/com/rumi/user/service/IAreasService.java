package com.rumi.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rumi.user.pojo.Areas;

import java.util.List;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/17 22:38
 * @Description: Areas业务层接口
 */
public interface IAreasService extends IService<Areas> {
    IPage<Areas> findPage(Areas areas, int page, int size);
    IPage<Areas> findPage(int page, int size);

    List<Areas> findList(Areas areas);
    // 其他方法由 MyBatis-Plus 的 IService 提供
}