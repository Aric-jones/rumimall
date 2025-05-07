package com.rumi.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rumi.pojo.Brand;

import java.util.List;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author CSH
 * @since 2025-05-04
 */
public interface IBrandService extends IService<Brand> {

    /**
     * @param page
     * @param size
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rumi.pojo.Brand>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/4 21:45
     * @Description: 分页查询
     */
    IPage<Brand> findPage(Integer page, Integer size);

    
    /**
     * @param page
     * @param size
     * @param brand
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rumi.pojo.Brand>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/4 21:45
     * @Description: 条件分页查询
     */
    IPage<Brand> findPage(Integer page, Integer size,Brand brand);

    /**
     * @param id
     * @return java.util.List<com.rumi.pojo.Brand>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/7 21:46
     * @Description: 通过分类id查询排品牌信息
     */
    List<Brand> findByCategory(Integer id);
}
