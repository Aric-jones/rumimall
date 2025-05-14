package com.rumi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rumi.dao.ParaMapper;
import com.rumi.goods.pojo.Category;
import com.rumi.goods.pojo.Para;
import com.rumi.goods.pojo.Template;
import com.rumi.service.ICategoryService;
import com.rumi.service.IParaService;
import com.rumi.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author CSH
 * @since 2025-05-06
 */
@Service
public class ParaServiceImpl extends ServiceImpl<ParaMapper, Para> implements IParaService {

    @Autowired
    private ITemplateService templateService;

    @Autowired
    private ICategoryService categoryService;

    /**
     * @param para
     * @param page
     * @param size
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rumi.pojo.Para>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:34
     * @Description: Para分页条件搜索实现
     */
    @Override
    public IPage<Para> findPage(Para para, int page, int size) {
        LambdaQueryWrapper<Para> wrapper = getParaLambdaQueryWrapper(para);
        Page<Para> paraPage = new Page<>(page, size);
        return this.page(paraPage, wrapper);
    }


    /**
     * @param page
     * @param size
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rumi.pojo.Para>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:34
     * @Description: Para分页搜索实现
     */
    @Override
    public IPage<Para> findPage(int page, int size) {
        Page<Para> paraPage = new Page<>(page, size);
        return this.page(paraPage);
    }

    /**
     * @param para
     * @return java.util.List<com.rumi.pojo.Para>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:38
     * @Description: 多条件搜索参数数据
     */
    @Override
    public List<Para> findList(Para para) {
        LambdaQueryWrapper<Para> wrapper = getParaLambdaQueryWrapper(para);
        return this.list(wrapper);
    }

    /**
     * @param id
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:42
     * @Description: 根据id删除
     */
    @Override
    @Transactional
    public void deleteById(Integer id) {
        Para para = this.getById(id);
        updateParaNum(para, -1);
        boolean remove = this.removeById(id);
        if (!remove) {
            throw new RuntimeException("删除失败");
        }
    }

    /**
     * @param para
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:44
     * @Description: 添加
     */
    @Override
    @Transactional
    public void add(Para para) {
        updateParaNum(para, 1);
        boolean save = this.save(para);
        if (!save) {
            throw new RuntimeException("添加失败");
        }
    }

    /**
     * @param categoryId
     * @return java.util.List<com.rumi.pojo.Para>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/7 22:03
     * @Description: 根据分类id查询对应参数列表
     */
    @Override
    public List<Para> findParaByCategoryId(Integer categoryId) {
        // 获取模板id
        Category category = categoryService.getById(categoryId);
        // 根据模板id查询参数列表
        return this.list(new LambdaQueryWrapper<Para>().eq(Para::getTemplateId, category.getTemplateId()));
    }


    /**
     * @param para
     * @param count
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:17
     * @Description: 修改模版参数数量
     */
    public void updateParaNum(Para para, int count) {
        //修改模板数量统计
        Template template = templateService.getById(para.getTemplateId());
        template.setParaNum(template.getParaNum() + count);
        templateService.updateById(template);
    }


    /**
     * @param para
     * @return com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<com.rumi.pojo.Para>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:54
     * @Description: 构建查询条件
     */
    private static LambdaQueryWrapper<Para> getParaLambdaQueryWrapper(Para para) {
        return new LambdaQueryWrapper<Para>()
                .like(Objects.nonNull(para.getName()), Para::getName, para.getName())
                .eq(Objects.nonNull(para.getTemplateId()), Para::getTemplateId, para.getTemplateId())
                .eq(Objects.nonNull(para.getSeq()), Para::getSeq, para.getSeq())
                .eq(Objects.nonNull(para.getId()), Para::getId, para.getId())
                .eq(Objects.nonNull(para.getOptions()), Para::getOptions, para.getOptions());
    }
}
