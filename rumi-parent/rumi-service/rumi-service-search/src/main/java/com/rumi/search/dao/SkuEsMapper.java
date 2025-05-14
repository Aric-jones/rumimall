package com.rumi.search.dao;

import com.rumi.search.pojo.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuEsMapper extends ElasticsearchRepository<SkuInfo, Long> {
}