package com.rumi.search.service;

import java.util.Map;

/**
 * @ClassName: SkuService
 * @Description:
 * @Author: CSH
 * @Date: 2025-05-14 21:05
 */
public interface ISkuService {

    /**
     * 导出数据到ES
     */
    void importDate();

    /**
     * 搜索
     */
    Map<String, Object> search(Map<String, Object> searchMap);
}
