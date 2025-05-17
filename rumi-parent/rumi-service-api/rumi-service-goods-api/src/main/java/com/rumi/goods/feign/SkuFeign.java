package com.rumi.goods.feign;


import com.rumi.common.entity.Result;
import com.rumi.goods.pojo.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/14 20:51
 * @Description:
 */
@FeignClient(value = "goods",contextId="goodsSku")
@RequestMapping("/sku")
public interface SkuFeign {
    /**
     * @param status
     * @return com.rumi.common.entity.Result<java.util.List < com.rumi.goods.pojo.Sku>>
     * @Author:CSH
     * @Updator:CSH
     * @Date 2025/5/14 20:51
     * @Description: 查询符合条件的状态的SKU的列表
     */
    @GetMapping("/status/{status}")
    Result<List<Sku>> findByStatus(@PathVariable(name = "status") String status);


    /**
     * @param sku
     * @return com.rumi.common.entity.Result<java.util.List < com.rumi.goods.pojo.Sku>>
     * @author:CSH
     * @updator:CSH
     * @date 2025/5/17 16:48
     * @description: 根据条件搜索
     */
    @PostMapping(value = "/search" )
    public Result<List<Sku>> findList(@RequestBody(required = false) Sku sku);

}
