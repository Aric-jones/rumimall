package com.rumi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rumi.dao.CategoryMapper;
import com.rumi.goods.pojo.Category;
import com.rumi.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author CSH
 * @since 2025-05-06
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

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
    @Override
    public IPage<Category> findPage(Category category, int page, int size) {
        LambdaQueryWrapper<Category> wrapper = getCategoryLambdaQueryWrapper(category);
        Page<Category> categoryPage = new Page<>(page, size);
        return this.page(categoryPage, wrapper);
    }



    /**
     * @param page
     * @param size
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rumi.pojo.Category>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 23:01
     * @Description: Category分页搜索实现
     */
    @Override
    public IPage<Category> findPage(int page, int size) {
        Page<Category> categoryPage = new Page<>(page, size);
        return this.page(categoryPage);
    }

    /**
     * @param category
     * @return java.util.List<com.rumi.pojo.Category>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 23:05
     * @Description: 多条件搜索分类数据
     */
    @Override
    public List<Category> findList(Category category) {
        LambdaQueryWrapper<Category> wrapper = getCategoryLambdaQueryWrapper(category);
        return this.list(wrapper);
    }

    /**
     * @param pid
     * @return java.util.List<com.rumi.pojo.Category>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 23:09
     * @Description: 根据父id查询
     */
    @Override
    public List<Category> findByParentId(Integer pid) {

        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<Category>().eq(Category::getParentId, pid);
        return this.list(wrapper);
    }


    private static LambdaQueryWrapper<Category> getCategoryLambdaQueryWrapper(Category category) {
        return new LambdaQueryWrapper<Category>()
                .like(Objects.nonNull(category.getName()), Category::getName, category.getName())
                .eq(Objects.nonNull(category.getGoodsNum()), Category::getGoodsNum, category.getGoodsNum())
                .eq(Objects.nonNull(category.getIsShow()), Category::getIsShow, category.getIsShow())
                .eq(Objects.nonNull(category.getIsMenu()), Category::getIsMenu, category.getIsMenu())
                .eq(Objects.nonNull(category.getSeq()), Category::getSeq, category.getSeq())
                .eq(Objects.nonNull(category.getParentId()), Category::getParentId, category.getParentId());
    }
}
