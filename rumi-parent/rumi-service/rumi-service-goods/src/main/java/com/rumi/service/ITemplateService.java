package com.rumi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rumi.goods.pojo.Template;

import java.util.List;

/**
 * @author CSH
 * @since 2025-05-06
 */
public interface ITemplateService extends IService<Template> {


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
    IPage<Template> findPage(Template template, int page, int size);


    /**
     * @param page
     * @param size
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rumi.pojo.Template>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:50
     * @Description: Template分页搜索实现
     */
    IPage<Template> findPage(int page, int size);

    
    /**
     * @param template
     * @return java.util.List<com.rumi.pojo.Template>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 21:55
     * @Description: 多条件搜索品牌数据
     */
    List<Template> findList(Template template);
}
