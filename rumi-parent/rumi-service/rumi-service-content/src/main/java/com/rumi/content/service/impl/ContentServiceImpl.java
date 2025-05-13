package com.rumi.content.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rumi.content.dao.ContentMapper;
import com.rumi.content.service.IContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rumi.content.pojo.Content;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CSH
 * @since 2025-05-13
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IContentService {


    /**
     * 条件分页查询
     *
     * @param content
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Content> findPage(Content content, int page, int size) {
        // 构建查询条件
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        if (content != null) {
            if (!org.springframework.util.StringUtils.isEmpty(content.getId())) {
                queryWrapper.eq("id", content.getId());
            }
            if (!org.springframework.util.StringUtils.isEmpty(content.getCategoryId())) {
                queryWrapper.eq("category_id", content.getCategoryId());
            }
            if (!org.springframework.util.StringUtils.isEmpty(content.getTitle())) {
                queryWrapper.like("title", content.getTitle());
            }
            if (!org.springframework.util.StringUtils.isEmpty(content.getUrl())) {
                queryWrapper.eq("url", content.getUrl());
            }
            if (!org.springframework.util.StringUtils.isEmpty(content.getPic())) {
                queryWrapper.eq("pic", content.getPic());
            }
            if (!org.springframework.util.StringUtils.isEmpty(content.getStatus())) {
                queryWrapper.eq("status", content.getStatus());
            }
            if (!org.springframework.util.StringUtils.isEmpty(content.getSortOrder())) {
                queryWrapper.eq("sort_order", content.getSortOrder());
            }
        }
        return baseMapper.selectPage(new Page<>(page, size), queryWrapper);
    }

    /**
     * @param content
     * @return java.util.List<com.rumi.contnet.pojo.Content>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/13 19:33
     * @Description: 条件查询
     */
    @Override
    public List<Content> findList(Content content) {
        // 构建查询条件
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        if (content != null) {
            if (!org.springframework.util.StringUtils.isEmpty(content.getId())) {
                queryWrapper.eq("id", content.getId());
            }
            if (!org.springframework.util.StringUtils.isEmpty(content.getCategoryId())) {
                queryWrapper.eq("category_id", content.getCategoryId());
            }
            if (!org.springframework.util.StringUtils.isEmpty(content.getTitle())) {
                queryWrapper.like("title", content.getTitle());
            }
            if (!org.springframework.util.StringUtils.isEmpty(content.getUrl())) {
                queryWrapper.eq("url", content.getUrl());
            }
            if (!org.springframework.util.StringUtils.isEmpty(content.getPic())) {
                queryWrapper.eq("pic", content.getPic());
            }
            if (!org.springframework.util.StringUtils.isEmpty(content.getStatus())) {
                queryWrapper.eq("status", content.getStatus());
            }
            if (!org.springframework.util.StringUtils.isEmpty(content.getSortOrder())) {
                queryWrapper.eq("sort_order", content.getSortOrder());
            }
        }
        return this.list(queryWrapper);
    }

}
