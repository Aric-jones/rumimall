package com.rumi.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rumi.user.pojo.OauthClientDetails;

import java.util.List;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/17 22:38
 * @Description: OauthClientDetails业务层接口
 */
public interface IOauthClientDetailsService extends IService<OauthClientDetails> {
    IPage<OauthClientDetails> findPage(OauthClientDetails oauthClientDetails, int page, int size);
    IPage<OauthClientDetails> findPage(int page, int size);

    List<OauthClientDetails> findList(OauthClientDetails oauthClientDetails);
    // 其他方法由 MyBatis-Plus 的 IService 提供
}