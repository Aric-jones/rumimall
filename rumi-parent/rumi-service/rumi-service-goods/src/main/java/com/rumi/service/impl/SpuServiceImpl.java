package com.rumi.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rumi.common.constant.enums.CheckStatusEnum;
import com.rumi.common.constant.enums.SaleStatusEnum;
import com.rumi.common.entity.IdWorker;
import com.rumi.dao.SpuMapper;
import com.rumi.pojo.*;
import com.rumi.service.IBrandService;
import com.rumi.service.ICategoryService;
import com.rumi.service.ISkuService;
import com.rumi.service.ISpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements ISpuService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private IBrandService brandService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ISkuService skuService;

    @Override
    public IPage<Spu> findPage(Spu spu, int page, int size) {
        LambdaQueryWrapper<Spu> wrapper = buildQueryWrapper(spu);
        Page<Spu> pageParam = new Page<>(page, size);
        return this.page(pageParam, wrapper);
    }

    @Override
    public IPage<Spu> findPage(int page, int size) {
        Page<Spu> pageParam = new Page<>(page, size);
        return this.page(pageParam);
    }

    @Override
    public List<Spu> findList(Spu spu) {
        LambdaQueryWrapper<Spu> wrapper = buildQueryWrapper(spu);
        return this.list(wrapper);
    }

    /**
     * @param goods
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/7 22:39
     * @Description: 新增商品信息（SPU+SKU）
     */
    @Override
    public void saveByGoods(Goods goods) {
        Spu spu = goods.getSpu();
        if (spu.getId()==null) {
            // 添加一条SPU
            spu.setId(idWorker.nextId());
            this.save(spu);
        } else {
            // 修改SPU
            this.updateById(spu);
            // 删除SKU
            skuService.remove(new LambdaQueryWrapper<Sku>().eq(Sku::getSpuId, spu.getId()));
        }

        // 添加多条SKU
        List<Sku> skuList = goods.getSkuList();
        Brand brand = brandService.getById(spu.getBrandId());
        Category category = categoryService.getById(spu.getCategory3Id());
        for (Sku sku : skuList) {
            sku.setId(idWorker.nextId());
            Map<String,String> map = JSON.parseObject(sku.getSpec(), Map.class);
            if(map == null){
                sku.setSpec("{}");
            }
            String name = spu.getName();
            for (String key : map.keySet()){
                name = name + " "+map.get(key);
            }
            sku.setSpuId(spu.getId());
            sku.setCategoryId(spu.getCategory3Id());
            sku.setCategoryName(category.getName());
            sku.setBrandName(brand.getName());
        }
        skuService.saveBatch(skuList);
    }

    /**
     * @param id
     * @return com.rumi.pojo.Goods
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/7 23:00
     * @Description: 根据id查询商品信息
     */
    @Override
    public Goods findGoodsById(Long id) {
        // 查询spu
        Spu spu = this.getById(id);

        // 查询sku列表
        List<Sku> skuList = skuService.list(new LambdaQueryWrapper<Sku>().eq(Sku::getSpuId, id));

        return Goods.builder().spu(spu).skuList(skuList).build();
    }

    /**
     * @param id
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/8 21:16
     * @Description: 商品审核
     */
    @Override
    public void auditSpu(Long id) {
        Spu spu = this.getById(id);
        if (Objects.isNull(spu)) {
            throw new RuntimeException("商品不存在");
        }
        spu.setIsMarketable(SaleStatusEnum.ON_SALE.getCode());
        spu.setStatus(CheckStatusEnum.CHECK_PASS.getCode());
        this.updateById(spu);
    }

    /**
     * @param id
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/8 21:16
     * @Description: 商品下架
     */
    @Override
    public void pullSpu(Long id) {
        Spu spu = this.getById(id);

        if (Objects.isNull(spu)) {
            throw new RuntimeException("商品不存在");
        }

        if(!CheckStatusEnum.CHECK_PASS.getCode().equals(spu.getStatus()) || !SaleStatusEnum.ON_SALE.getCode().equals(spu.getIsMarketable())){
            throw new RuntimeException("商品必须要审核或者商品必须要是上架的状态");
        }
        spu.setIsMarketable(SaleStatusEnum.NOT_OFF_SALE.getCode());
        this.updateById(spu);
    }

    /**
     * @param id
     * @return void
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/8 21:26
     * @Description: 商品上架
     */
    @Override
    public void putSpu(Long id) {
        Spu spu = this.getById(id);
        if (Objects.isNull(spu)) {
            throw new RuntimeException("商品不存在");
        }
        if(CheckStatusEnum.CHECK_NOT_PASS.getCode().equals(spu.getStatus())){
            throw new RuntimeException("商品审核未通过");
        }
        spu.setIsMarketable(SaleStatusEnum.ON_SALE.getCode());
        this.updateById(spu);
    }

    /**
     * @param ids
     * @return int
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/8 21:31
     * @Description: 批量上架
     */
    @Override
    public void putMany(Long[] ids) {
        List<Long> list = Arrays.asList(ids);
        Spu spu = new Spu();
        spu.setIsMarketable(SaleStatusEnum.ON_SALE.getCode());
        LambdaQueryWrapper<Spu> wrapper = new LambdaQueryWrapper<Spu>().in(Spu::getId, list).eq(Spu::getStatus, CheckStatusEnum.CHECK_PASS.getCode());
        this.update(spu, wrapper);
    }

    /**
     * 构建查询条件
     */
    private LambdaQueryWrapper<Spu> buildQueryWrapper(Spu spu) {
        return new LambdaQueryWrapper<Spu>()
                .eq(Objects.nonNull(spu.getId()), Spu::getId, spu.getId())
                .eq(Objects.nonNull(spu.getBrandId()), Spu::getBrandId, spu.getBrandId())
                .eq(Objects.nonNull(spu.getCategory1Id()), Spu::getCategory1Id, spu.getCategory1Id())
                .eq(Objects.nonNull(spu.getCategory2Id()), Spu::getCategory2Id, spu.getCategory2Id())
                .eq(Objects.nonNull(spu.getCategory3Id()), Spu::getCategory3Id, spu.getCategory3Id())
                .eq(Objects.nonNull(spu.getTemplateId()), Spu::getTemplateId, spu.getTemplateId())
                .like(Objects.nonNull(spu.getName()), Spu::getName, spu.getName())
                .like(Objects.nonNull(spu.getCaption()), Spu::getCaption, spu.getCaption())
                .eq(Objects.nonNull(spu.getIsMarketable()), Spu::getIsMarketable, spu.getIsMarketable())
                .eq(Objects.nonNull(spu.getIsEnableSpec()), Spu::getIsEnableSpec, spu.getIsEnableSpec())
                .eq(Objects.nonNull(spu.getStatus()), Spu::getStatus, spu.getStatus())
                .orderByDesc(Spu::getSaleNum);
    }
}