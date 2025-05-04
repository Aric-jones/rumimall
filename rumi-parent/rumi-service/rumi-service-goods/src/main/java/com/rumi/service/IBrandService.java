package com.rumi.service;


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

    List<Brand> findPage(Integer page, Integer size);
}
