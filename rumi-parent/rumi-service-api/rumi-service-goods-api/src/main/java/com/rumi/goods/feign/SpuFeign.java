package com.rumi.goods.feign;

import com.rumi.common.entity.Result;
import com.rumi.goods.pojo.Spu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: SpuFeign
 * @Description:
 * @Author: CSH
 * @Date: 2025-05-17 16:49
 */
@FeignClient(value = "goods",contextId="goodsSpu")
@RequestMapping("/spu")
public interface SpuFeign {

    @GetMapping("/{id}")
    Result<Spu> findById(@PathVariable(name = "id") Long id);
}