package com.rumi.content.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rumi.content.dao.ContentCategoryMapper;
import com.rumi.content.service.IContentCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rumi.content.pojo.ContentCategory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author CSH
 * @since 2025-05-13
 */
@Service
public class ContentCategoryServiceImpl extends ServiceImpl<ContentCategoryMapper, ContentCategory> implements IContentCategoryService {


    @Override
    public Page<ContentCategory> findPage(ContentCategory contentCategory, int page, int size) {
        QueryWrapper<ContentCategory> queryWrapper = buildQueryWrapper(contentCategory);
        return page(new Page<>(page, size), queryWrapper);
    }

    @Override
    public Page<ContentCategory> findPage(int page, int size) {
        return page(new Page<>(page, size));
    }

    /**
     * 条件查询
     *
     * @param contentCategory
     */
    @Override
    public List<ContentCategory> findList(ContentCategory contentCategory) {
        return this.list(buildQueryWrapper(contentCategory));
    }

    /**
     * 构建查询条件
     */
    private QueryWrapper<ContentCategory> buildQueryWrapper(ContentCategory contentCategory) {
        QueryWrapper<ContentCategory> queryWrapper = new QueryWrapper<>();
        if (contentCategory != null) {
            if (contentCategory.getId() != null) {
                queryWrapper.eq("id", contentCategory.getId());
            }
            if (contentCategory.getName() != null && !contentCategory.getName().isEmpty()) {
                queryWrapper.like("name", contentCategory.getName());
            }
        }
        return queryWrapper;
    }

}
