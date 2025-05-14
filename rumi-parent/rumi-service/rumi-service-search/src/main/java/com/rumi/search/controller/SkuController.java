package com.rumi.search.controller;

import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.search.service.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/search")
@CrossOrigin
public class SkuController {

    @Autowired
    private ISkuService skuService;

    /**
     * 导入数据
     * @return
     */
    @GetMapping("/import")
    public Result<String> search(){
        skuService.importDate();
        return new Result<String>(true, StatusCode.OK,"导入数据到索引库中成功！");
    }
}