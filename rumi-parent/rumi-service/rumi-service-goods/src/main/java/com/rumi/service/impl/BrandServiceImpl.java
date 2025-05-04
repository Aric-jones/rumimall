package com.rumi.service.impl;


import com.rumi.dao.BrandMapper;
import com.rumi.pojo.Brand;
import com.rumi.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author CSH
 * @since 2025-05-04
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

}
