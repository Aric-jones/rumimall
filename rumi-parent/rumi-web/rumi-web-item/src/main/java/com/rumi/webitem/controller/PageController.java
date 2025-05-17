package com.rumi.webitem.controller;

import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import com.rumi.webitem.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page")
public class PageController {

    @Autowired
    private PageService pageService;

    /**
     * 生成静态页面
     * @param id
     * @return
     */
    @RequestMapping("/createHtml/{id}")
    public Result<String> createHtml(@PathVariable(name="id") Long id){
        pageService.createPageHtml(id);
        return new Result<String>(true, StatusCode.OK,"ok");
    }
}