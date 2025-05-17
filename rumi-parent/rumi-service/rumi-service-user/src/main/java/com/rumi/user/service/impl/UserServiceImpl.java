package com.rumi.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rumi.user.dao.UserMapper;
import com.rumi.user.pojo.User;
import com.rumi.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<User> findPage(User user, int page, int size) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        if (user != null) {
            // 用户名
            if (user.getUsername() != null && !user.getUsername().isEmpty()) {
                queryWrapper.like("username", user.getUsername());
            }
            // 密码
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                queryWrapper.eq("password", user.getPassword());
            }
            // 注册手机号
            if (user.getPhone() != null && !user.getPhone().isEmpty()) {
                queryWrapper.eq("phone", user.getPhone());
            }
            // 注册邮箱
            if (user.getEmail() != null && !user.getEmail().isEmpty()) {
                queryWrapper.eq("email", user.getEmail());
            }
            // 创建时间
            if (user.getCreated() != null) {
                queryWrapper.eq("created", user.getCreated());
            }
            // 修改时间
            if (user.getUpdated() != null) {
                queryWrapper.eq("updated", user.getUpdated());
            }
            // 会员来源：1:PC，2：H5，3：Android，4：IOS
            if (user.getSourceType() != null) {
                queryWrapper.eq("source_type", user.getSourceType());
            }
            // 昵称
            if (user.getNickName() != null && !user.getNickName().isEmpty()) {
                queryWrapper.eq("nick_name", user.getNickName());
            }
            // 真实姓名
            if (user.getName() != null && !user.getName().isEmpty()) {
                queryWrapper.like("name", user.getName());
            }
            // 使用状态（1正常 0非正常）
            if (user.getStatus() != null) {
                queryWrapper.eq("status", user.getStatus());
            }
            // 头像地址
            if (user.getHeadPic() != null && !user.getHeadPic().isEmpty()) {
                queryWrapper.eq("head_pic", user.getHeadPic());
            }
            // QQ号码
            if (user.getQq() != null) {
                queryWrapper.eq("qq", user.getQq());
            }
            // 手机是否验证 （0否  1是）
            if (user.getIsMobileCheck() != null) {
                queryWrapper.eq("is_mobile_check", user.getIsMobileCheck());
            }
            // 邮箱是否检测（0否  1是）
            if (user.getIsEmailCheck() != null) {
                queryWrapper.eq("is_email_check", user.getIsEmailCheck());
            }
            // 性别，1男，0女
            if (user.getSex() != null) {
                queryWrapper.eq("sex", user.getSex());
            }
            // 会员等级
            if (user.getUserLevel() != null) {
                queryWrapper.eq("user_level", user.getUserLevel());
            }
            // 积分
            if (user.getPoints() != null) {
                queryWrapper.eq("points", user.getPoints());
            }
            // 经验值
            if (user.getExperienceValue() != null) {
                queryWrapper.eq("experience_value", user.getExperienceValue());
            }
            // 出生年月日
            if (user.getBirthday() != null) {
                queryWrapper.eq("birthday", user.getBirthday());
            }
            // 最后登录时间
            if (user.getLastLoginTime() != null) {
                queryWrapper.eq("last_login_time", user.getLastLoginTime());
            }
        }

        IPage<User> iPage = new Page<>(page, size);
        return this.page(iPage, queryWrapper);
    }

    @Override
    public IPage<User> findPage(int page, int size) {
        IPage<User> iPage = new Page<>(page, size);
        return this.page(iPage);
    }

    @Override
    public List<User> findList(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        if (user != null) {
            // 用户名
            if (user.getUsername() != null && !user.getUsername().isEmpty()) {
                queryWrapper.like("username", user.getUsername());
            }
            // 密码
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                queryWrapper.eq("password", user.getPassword());
            }
            // 注册手机号
            if (user.getPhone() != null && !user.getPhone().isEmpty()) {
                queryWrapper.eq("phone", user.getPhone());
            }
            // 注册邮箱
            if (user.getEmail() != null && !user.getEmail().isEmpty()) {
                queryWrapper.eq("email", user.getEmail());
            }
            // 创建时间
            if (user.getCreated() != null) {
                queryWrapper.eq("created", user.getCreated());
            }
            // 修改时间
            if (user.getUpdated() != null) {
                queryWrapper.eq("updated", user.getUpdated());
            }
            // 会员来源：1:PC，2：H5，3：Android，4：IOS
            if (user.getSourceType() != null) {
                queryWrapper.eq("source_type", user.getSourceType());
            }
            // 昵称
            if (user.getNickName() != null && !user.getNickName().isEmpty()) {
                queryWrapper.eq("nick_name", user.getNickName());
            }
            // 真实姓名
            if (user.getName() != null && !user.getName().isEmpty()) {
                queryWrapper.like("name", user.getName());
            }
            // 使用状态（1正常 0非正常）
            if (user.getStatus() != null) {
                queryWrapper.eq("status", user.getStatus());
            }
            // 头像地址
            if (user.getHeadPic() != null && !user.getHeadPic().isEmpty()) {
                queryWrapper.eq("head_pic", user.getHeadPic());
            }
            // QQ号码
            if (user.getQq() != null) {
                queryWrapper.eq("qq", user.getQq());
            }
            // 手机是否验证 （0否  1是）
            if (user.getIsMobileCheck() != null) {
                queryWrapper.eq("is_mobile_check", user.getIsMobileCheck());
            }
            // 邮箱是否检测（0否  1是）
            if (user.getIsEmailCheck() != null) {
                queryWrapper.eq("is_email_check", user.getIsEmailCheck());
            }
            // 性别，1男，0女
            if (user.getSex() != null) {
                queryWrapper.eq("sex", user.getSex());
            }
            // 会员等级
            if (user.getUserLevel() != null) {
                queryWrapper.eq("user_level", user.getUserLevel());
            }
            // 积分
            if (user.getPoints() != null) {
                queryWrapper.eq("points", user.getPoints());
            }
            // 经验值
            if (user.getExperienceValue() != null) {
                queryWrapper.eq("experience_value", user.getExperienceValue());
            }
            // 出生年月日
            if (user.getBirthday() != null) {
                queryWrapper.eq("birthday", user.getBirthday());
            }
            // 最后登录时间
            if (user.getLastLoginTime() != null) {
                queryWrapper.eq("last_login_time", user.getLastLoginTime());
            }
        }

        return this.list(queryWrapper);
    }
}