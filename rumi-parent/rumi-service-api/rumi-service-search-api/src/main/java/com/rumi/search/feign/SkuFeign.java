package com.rumi.search.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @ClassName: SkuFeign
 * @Description:
 * @Author: CSH
 * @Date: 2025-05-16 22:03
 */
@FeignClient(name="search")
@RequestMapping("/search")
public interface SkuFeign {
    @GetMapping
    public Map<String, Object> search(@RequestParam(required = false) Map<String, Object> searchMap);
}
