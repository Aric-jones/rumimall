package com.rumi.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rumi.user.pojo.User;

import java.util.List;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/17 22:38
 * @Description: User业务层接口
 */
public interface IUserService extends IService<User> {
    IPage<User> findPage(User user, int page, int size);
    IPage<User> findPage(int page, int size);
    List<User> findList(User user);
}