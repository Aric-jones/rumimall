package com.rumi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rumi.dao.SpecMapper;
import com.rumi.pojo.Category;
import com.rumi.pojo.Spec;
import com.rumi.pojo.Template;
import com.rumi.service.ICategoryService;
import com.rumi.service.ISpecService;
import com.rumi.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author CSH
 * @since 2025-05-06
 */
@Service
public class SpecServiceImpl extends ServiceImpl<SpecMapper, Spec> implements ISpecService {

    @Autowired
    private ITemplateService templateService;

    @Autowired
    private ICategoryService categoryService;

    /**
     * @param spec
     * @param page
     * @param size
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rumi.pojo.Spec>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:06
     * @Description: Spec分页条件搜索实现
     */
    @Override
    public IPage<Spec> findPage(Spec spec, int page, int size) {
        LambdaQueryWrapper<Spec> wrapper = getSpecLambdaQueryWrapper(spec);
        Page<Spec> specPage = new Page<>(page, size);
        return this.page(specPage, wrapper);
    }



    /**
     * @param page
     * @param size
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rumi.pojo.Spec>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:06
     * @Description: Spec分页条件搜索实现
     */
    @Override
    public IPage<Spec> findPage(int page, int size) {
        Page<Spec> specPage = new Page<>(page, size);
        return this.page(specPage);
    }

    /**
     * @param spec
     * @return java.util.List<com.rumi.pojo.Spec>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:13
     * @Description: 多条件搜索规格数据
     */
    @Override
    public List<Spec> findList(Spec spec) {
        LambdaQueryWrapper<Spec> wrapper = getSpecLambdaQueryWrapper(spec);
        return this.list(wrapper);
    }

    /**
     * @param id
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:16
     * @Description: 根据id删除
     */
    @Override
    @Transactional
    public void deleteById(Integer id) {
        Spec spec = this.getById(id);
        updateSpecNum(spec, -1);
        boolean remove = this.removeById(id);
        if (!remove) {
            throw new RuntimeException("删除失败");
        }
    }

    /**
     * @param spec
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:23
     * @Description: 添加
     */
    @Override
    public void add(Spec spec) {
        updateSpecNum(spec, 1);
        boolean save = this.save(spec);
        if (!save) {
            throw new RuntimeException("添加失败");
        }
    }

    /**
     * @param categoryId
     * @return java.util.List<com.rumi.pojo.Spec>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/7 21:54
     * @Description: 根据商品id查询对应的规格信息列表
     */
    @Override
    public List<Spec> findByCategoryId(Integer categoryId) {
        // 获取分类的模版id
        Category category = categoryService.getById(categoryId);
        // 根据模板id查询
        return this.list(new LambdaQueryWrapper<Spec>().eq(Spec::getTemplateId, category.getTemplateId()));
    }

    /**
     * @param spec
     * @param count
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:17
     * @Description: 修改模版规格数量
     */
    public void updateSpecNum(Spec spec, int count) {
        //修改模板数量统计
        Template template = templateService.getById(spec.getTemplateId());
        template.setSpecNum(template.getSpecNum() + count);
        templateService.updateById(template);
    }


    /**
     * @param spec
     * @return com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<com.rumi.pojo.Spec>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:53
     * @Description: 构建查询条件
     */
    private static LambdaQueryWrapper<Spec> getSpecLambdaQueryWrapper(Spec spec) {
        return new LambdaQueryWrapper<Spec>()
                .like(Objects.nonNull(spec.getName()), Spec::getName, spec.getName())
                .eq(Objects.nonNull(spec.getTemplateId()), Spec::getTemplateId, spec.getTemplateId())
                .eq(Objects.nonNull(spec.getSeq()), Spec::getSeq, spec.getSeq())
                .eq(Objects.nonNull(spec.getId()), Spec::getId, spec.getId())
                .eq(Objects.nonNull(spec.getOptions()), Spec::getOptions, spec.getOptions());
    }
}
