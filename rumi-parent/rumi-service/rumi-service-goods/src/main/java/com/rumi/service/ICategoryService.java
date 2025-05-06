package com.rumi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rumi.pojo.Category;

import java.util.List;

/**
 * @author CSH
 * @since 2025-05-06
 */
public interface ICategoryService extends IService<Category> {


    /**
     * @param category
     * @param page
     * @param size
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rumi.pojo.Category>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 23:01
     * @Description: Category分页条件搜索实现
     */
    IPage<Category> findPage(Category category, int page, int size);

    /**
     * @param page
     * @param size
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rumi.pojo.Category>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 23:01
     * @Description: Category分页搜索实现
     */
    IPage<Category> findPage(int page, int size);

    /**
     * @param category
     * @return java.util.List<com.rumi.pojo.Category>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 23:05
     * @Description: 多条件搜索分类数据
     */
    List<Category> findList(Category category);

    /**
     * @param pid
     * @return java.util.List<com.rumi.pojo.Category>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 23:09
     * @Description: 根据父id查询
     */
    List<Category> findByParentId(Integer pid);
}
