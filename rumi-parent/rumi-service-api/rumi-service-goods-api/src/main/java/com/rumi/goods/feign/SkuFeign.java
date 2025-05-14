package com.rumi.goods.feign;


import com.rumi.common.entity.Result;
import com.rumi.goods.pojo.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/14 20:51
 * @Description:
 */
@FeignClient(value = "goods")
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

}
