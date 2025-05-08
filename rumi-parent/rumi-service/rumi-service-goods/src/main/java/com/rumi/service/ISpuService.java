package com.rumi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rumi.pojo.Goods;
import com.rumi.pojo.Spu;

import java.util.List;

public interface ISpuService extends IService<Spu> {
    /**
     * 分页条件查询
     */
    IPage<Spu> findPage(Spu spu, int page, int size);

    /**
     * 简单分页查询
     */
    IPage<Spu> findPage(int page, int size);

    /**
     * 条件查询列表
     */
    List<Spu> findList(Spu spu);

    /**
     * @param goods
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/7 22:39
     * @Description: 新增商品信息（SPU+SKU）
     */
    void saveByGoods(Goods goods);

    /**
     * @param id
     * @return com.rumi.pojo.Goods
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/7 23:00
     * @Description: 根据id查询商品信息
     */
    Goods findGoodsById(Long id);

    /**
     * @param id
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/8 21:16
     * @Description: 商品审核
     */
    void auditSpu(Long id);


    /**
     * @param id
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/8 21:16
     * @Description: 商品下架
     */
    void pullSpu(Long id);

    /**
     * @param id
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/8 21:26
     * @Description: 商品上架
     */
    void putSpu(Long id);

    /**
     * @param ids
     * @return int
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/8 21:31
     * @Description: 批量上架
     */
    void putMany(Long[] ids);
}