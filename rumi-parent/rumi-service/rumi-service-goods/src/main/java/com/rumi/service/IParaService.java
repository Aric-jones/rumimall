package com.rumi.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rumi.pojo.Para;

import java.util.List;

/**
 * @author CSH
 * @since 2025-05-06
 */
public interface IParaService extends IService<Para> {

    
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
    IPage<Para> findPage(Para para, int page, int size);


    /**
     * @param page
     * @param size
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rumi.pojo.Para>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:34
     * @Description: Para分页搜索实现
     */
    IPage<Para> findPage(int page, int size);

    /**
     * @param para
     * @return java.util.List<com.rumi.pojo.Para>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:38
     * @Description: 多条件搜索参数数据
     */
    List<Para> findList(Para para);


    /**
     * @param id
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:42
     * @Description: 根据id删除
     */
    void deleteById(Integer id);


    /**
     * @param para
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:44
     * @Description: 添加
     */
    void add(Para para);
}
