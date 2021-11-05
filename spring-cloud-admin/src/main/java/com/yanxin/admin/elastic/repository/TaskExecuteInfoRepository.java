package com.yanxin.admin.elastic.repository;

import com.yanxin.admin.elastic.entity.TaskExecuteInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-09-23 15:46
 */
public interface TaskExecuteInfoRepository extends ElasticsearchRepository<TaskExecuteInfo, String> {


}
