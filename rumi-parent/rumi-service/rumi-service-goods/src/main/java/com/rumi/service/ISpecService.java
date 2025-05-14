package com.rumi.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rumi.goods.pojo.Spec;

import java.util.List;

/**
 * @author CSH
 * @since 2025-05-06
 */
public interface ISpecService extends IService<Spec> {


    /**
     * @param spec
     * @param page
     * @param size
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rumi.pojo.Spec>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:06
     * @Description: Spec分页条件搜索实现
     */
    IPage<Spec> findPage(Spec spec, int page, int size);


    /**
     * @param page
     * @param size
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rumi.pojo.Spec>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:06
     * @Description: Spec分页条件搜索实现
     */
    IPage<Spec> findPage(int page, int size);

    /**
     * @param spec
     * @return java.util.List<com.rumi.pojo.Spec>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:13
     * @Description: 多条件搜索规格数据
     */
    List<Spec> findList(Spec spec);

    /**
     * @param id
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:16
     * @Description: 根据id删除
     */
    void deleteById(Integer id);

    /**
     * @param spec
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/6 22:23
     * @Description: 添加
     */
    void add(Spec spec);

    /**
     * @param id
     * @return java.util.List<com.rumi.pojo.Spec>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/7 21:54
     * @Description: 根据商品id查询对应的规格信息列表
     */
    List<Spec> findByCategoryId(Integer id);
}
