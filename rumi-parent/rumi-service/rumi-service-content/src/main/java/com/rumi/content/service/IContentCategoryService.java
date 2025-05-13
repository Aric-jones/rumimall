package com.rumi.content.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rumi.content.pojo.ContentCategory;

import java.util.List;

/**
 * @author CSH
 * @since 2025-05-13
 */
public interface IContentCategoryService extends IService<ContentCategory> {

    /**
     * 分页条件查询
     */
    Page<ContentCategory> findPage(ContentCategory contentCategory, int page, int size);

    /**
     * 分页查询
     */
    Page<ContentCategory> findPage(int page, int size);


    /**
     * 条件查询
     */
    List<ContentCategory> findList(ContentCategory contentCategory);
}
