package com.yanxin.admin.elastic.service;

import com.yanxin.admin.elastic.entity.TaskExecuteInfo;
import com.yanxin.admin.elastic.repository.TaskExecuteInfoRepository;
import org.springframework.stereotype.Service;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-12-21 16:01
 */
@Service
public class TaskExecuteInfoService {

    private TaskExecuteInfoRepository taskExecuteInfoRepository;

    public TaskExecuteInfoService(TaskExecuteInfoRepository taskExecuteInfoRepository) {
        this.taskExecuteInfoRepository = taskExecuteInfoRepository;
    }

    public String saveTask(TaskExecuteInfo taskExecuteInfo) {

        final TaskExecuteInfo task = taskExecuteInfoRepository.save(taskExecuteInfo);

        return task.get_id();
    }
}
