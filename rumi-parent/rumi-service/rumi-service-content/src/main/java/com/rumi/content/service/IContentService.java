package com.rumi.content.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rumi.content.pojo.Content;

import java.util.List;

/**
 * @author CSH
 * @since 2025-05-13
 */
public interface IContentService extends IService<Content> {

    

    /**
     * 条件分页查询
     * @param content
     * @param page
     * @param size
     * @return
     */
    Page<Content> findPage(Content content, int page, int size);

    
    /**
     * @param content
     * @return java.util.List<com.rumi.contnet.pojo.Content>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/13 19:33
     * @Description: 条件查询
     */
    List<Content> findList(Content content);
}
