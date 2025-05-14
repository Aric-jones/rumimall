package com.rumi.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rumi.goods.pojo.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 品牌表 Mapper 接口
 * </p>
 *
 * @author CSH
 * @since 2025-05-04
 */
@Mapper
public interface BrandMapper extends BaseMapper<Brand> {

    /**
     * @param id
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/7 21:47
     * @Description: 通过分类id查询排品牌信息
     */
    @Select("select tb.* from tb_brand tb,tb_category_brand tcb where tb.id = tcb.brand_id and tcb.category_id = #{id}")
    List<Brand> findByCategory(Integer id);
}
