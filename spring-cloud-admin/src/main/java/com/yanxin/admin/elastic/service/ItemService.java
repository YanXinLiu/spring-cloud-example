package com.yanxin.admin.elastic.service;

import com.yanxin.admin.elastic.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-09-23 15:34
 */
@Service
public class ItemService {

    /*@Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public void create() {
        elasticsearchOperations.createIndex(Item.class);
        elasticsearchOperations.putMapping(Item.class);
    }*/
}
