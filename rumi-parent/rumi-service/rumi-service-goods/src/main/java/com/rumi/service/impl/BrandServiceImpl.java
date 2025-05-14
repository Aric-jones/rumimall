package com.rumi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rumi.dao.BrandMapper;
import com.rumi.goods.pojo.Brand;
import com.rumi.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public IPage<Brand> findPage(Integer page, Integer size) {
        // 实现分页查询
        Page<Brand> objectPage = new Page<>(page, size);
        return this.page(objectPage);

    }

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
    @Override
    public IPage<Brand> findPage(Integer page, Integer size, Brand brand) {
        Page<Brand> objectPage = new Page<>(page, size);
        LambdaQueryWrapper<Brand> wrapper = new LambdaQueryWrapper<Brand>()
                .like(brand.getName() != null, Brand::getName, brand.getName())
                .eq(brand.getImage() != null, Brand::getImage, brand.getImage())
                .eq(brand.getLetter() != null, Brand::getLetter, brand.getLetter())
                .eq(brand.getSeq() != null, Brand::getSeq, brand.getSeq());
        return this.page(objectPage, wrapper);
    }

    /**
     * @param id
     * @return java.util.List<com.rumi.pojo.Brand>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/7 21:46
     * @Description: 通过分类id查询排品牌信息
     */
    @Override
    public List<Brand> findByCategory(Integer id) {
        return brandMapper.findByCategory(id);
    }
}
