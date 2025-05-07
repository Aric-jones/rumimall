package com.rumi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rumi.dao.TemplateMapper;
import com.rumi.pojo.Template;
import com.rumi.service.ITemplateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author CSH
 * @since 2025-05-06
 */
@Service
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper, Template> implements ITemplateService {

    /**
     * @param template
     * @param page
     * @param size
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rumi.pojo.Template>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:50
     * @Description: Template分页条件搜索实现
     */
    @Override
    public IPage<Template> findPage(Template template, int page, int size) {
        LambdaQueryWrapper<Template> wrapper = new LambdaQueryWrapper<Template>().like(Objects.nonNull(template.getName()), Template::getName, template.getName());
        Page<Template> templatePage = new Page<Template>(page, size);
        return this.page(templatePage, wrapper);
    }

    /**
     * @param page
     * @param size
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rumi.pojo.Template>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:50
     * @Description: Template分页搜索实现
     */
    @Override
    public IPage<Template> findPage(int page, int size) {
        Page<Template> templatePage = new Page<Template>(page, size);
        return this.page(templatePage);
    }

    /**
     * @param template
     * @return java.util.List<com.rumi.pojo.Template>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:55
     * @Description: 多条件搜索品牌数据
     */
    @Override
    public List<Template> findList(Template template) {
        LambdaQueryWrapper<Template> wrapper = new LambdaQueryWrapper<Template>().like(Objects.nonNull(template.getName()), Template::getName, template.getName());
        return this.list(wrapper);
    }
}
