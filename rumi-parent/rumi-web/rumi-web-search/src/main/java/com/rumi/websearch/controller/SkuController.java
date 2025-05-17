package com.rumi.websearch.controller;

import com.rumi.common.entity.Page;
import com.rumi.search.feign.SkuFeign;
import com.rumi.search.pojo.SkuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/search")
public class SkuController {

    @Autowired
    private SkuFeign skuFeign;


    @GetMapping("/list")
    public String search(@RequestParam(required = false) Map<String, Object> searchMap, Model model) {
        //1.调用搜索微服务的 feign  根据搜索的条件参数 查询 数据
        Map<String, Object> resultmap = skuFeign.search(searchMap);
        //2.将数据设置到model中     (模板文件中 根据th:标签数据展示)
        //搜索的结果设置
        model.addAttribute("result", resultmap);

        //3.设置搜索的条件 回显
        model.addAttribute("searchMap", searchMap);

        //4.记住之前的URL
        //拼接url
        String[] urls = url(searchMap);
        model.addAttribute("url", urls[0]);
        model.addAttribute("sorturl", urls[1]);

        //创建一个分页的对象  可以获取当前页 和总个记录数和显示的页码(以当前页为中心的5个页码)
        Page<SkuInfo> infoPage = new Page<SkuInfo>(
                Long.parseLong(resultmap.get("total").toString()),
                Integer.parseInt(resultmap.get("pageNum").toString()),
                Integer.parseInt(resultmap.get("pageSize").toString())
        );

        model.addAttribute("page", infoPage);
        //3.返回
        return "search";
    }

    private String[] url (Map<String, Object> searchMap) {
        StringBuilder url = new StringBuilder("/search/list");
        StringBuilder sorturl = new StringBuilder("/search/list");
        if (searchMap != null && !searchMap.isEmpty()) {
            url.append("?");
            sorturl.append("?");
            for (Map.Entry<String, Object> stringStringEntry : searchMap.entrySet()) {
                String key = stringStringEntry.getKey();
                String value = (String) stringStringEntry.getValue();
                if ("pageNum".equals(key)) {
                    continue;
                }
                url.append(key).append("=").append(value).append("&");
                if("sortField".equals(key)||"sortRule".equals(key)){
                    continue;
                }
                sorturl.append(key).append("=").append(value).append("&");
            }

            //去掉多余的&
            if (url.lastIndexOf("&") != -1) {
                url = new StringBuilder(url.substring(0, url.lastIndexOf("&")));
            }
            if (sorturl.lastIndexOf("&") != -1) {
                sorturl = new StringBuilder(sorturl.substring(0, sorturl.lastIndexOf("&")));
            }

        }
        return new String[]{url.toString(),sorturl.toString()};
    }
}
