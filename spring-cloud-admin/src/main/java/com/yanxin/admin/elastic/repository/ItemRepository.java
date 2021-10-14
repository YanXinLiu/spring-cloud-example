package com.yanxin.admin.elastic.repository;

import com.yanxin.admin.elastic.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-09-23 15:46
 */
public interface ItemRepository extends ElasticsearchRepository<Item, String> {

}
