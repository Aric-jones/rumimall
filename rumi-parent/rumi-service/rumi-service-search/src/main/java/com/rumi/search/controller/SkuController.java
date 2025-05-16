package com.rumi.search.controller;

import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.search.service.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/search")
@CrossOrigin
public class SkuController {

    @Autowired
    private ISkuService skuService;

    /**
     * @return com.rumi.common.entity.Result<java.lang.String>
     * @author: CSH
     * @updator: CSH
     * @date 2025/5/16 16:06
     * @description:
     */
    @GetMapping("/import")
    public Result<String> search() {
        skuService.importDate();
        return new Result<String>(true, StatusCode.OK, "导入数据到索引库中成功！");
    }

    /**
     * @param searchMap
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author: CSH
     * @updator: CSH
     * @date 2025/5/16 16:15
     */
    @GetMapping
    public Map<String, Object> search(@RequestParam(required = false) Map<String, Object> searchMap) {
        return skuService.search(searchMap);
    }

}