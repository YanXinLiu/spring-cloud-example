package com.yanxin.nacos.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yanxin.nacos.entity.TaskExecuteInfo;
import com.yanxin.nacos.entity.TaskInfo;
import com.yanxin.nacos.entity.TaskResponse;
import com.yanxin.nacos.repository.TaskExecuteInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author kaka
 * @Date 2020-09-04
 */
@Slf4j
@Service
public class EsRequestService {

    @Autowired
    private TaskExecuteInfoRepository taskExecuteInfoRepository;

    public TaskExecuteInfo getByJobNum(String jobNum) {

        TaskInfo task = taskExecuteInfoRepository.findById(jobNum)
                .orElseThrow(() -> new RuntimeException(""));
        TaskExecuteInfo resInfo = new TaskExecuteInfo();
        BeanUtils.copyProperties(task, resInfo);
        if (task.getResponse() instanceof ArrayList) {
            List<TaskResponse> resList = JSONObject.parseArray(JSON.toJSONString(task.getResponse()), TaskResponse.class);
            resInfo.setResponse(resList);
        } else {
            log.info("[biz] elasticSearch 查询response失败: {}", task.getResponse());
            resInfo.setResponse(Collections.emptyList());
        }
        return resInfo;
    }
}
