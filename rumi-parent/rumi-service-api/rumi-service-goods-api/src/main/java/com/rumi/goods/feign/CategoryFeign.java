package com.rumi.goods.feign;

import com.rumi.common.entity.Result;
import com.rumi.goods.pojo.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: CategoryFeign
 * @Description:
 * @Author: CSH
 * @Date: 2025-05-17 16:46
 */
@FeignClient(value = "goods",contextId="goodsCategory")
@RequestMapping("/category")
public interface CategoryFeign {
    /**
     * @param id
     * @return com.rumi.common.entity.Result<com.rumi.goods.pojo.Category>
     * @author:CSH
     * @updator:CSH
     * @date 2025/5/17 16:47
     * @description: 获取分类对象信息
     */
    @GetMapping("/{id}")
    Result<Category> findById(@PathVariable(name = "id") Integer id);
}
