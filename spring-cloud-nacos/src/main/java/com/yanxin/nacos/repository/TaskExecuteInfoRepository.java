package com.yanxin.nacos.repository;

import com.yanxin.nacos.entity.TaskInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-09-23 15:46
 */
public interface TaskExecuteInfoRepository extends ElasticsearchRepository<TaskInfo, String> {


}
