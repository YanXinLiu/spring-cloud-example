package com.yanxin.admin.elastic.service;


import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.yanxin.admin.elastic.entity.TaskExecuteInfo;
import com.yanxin.admin.elastic.entity.TaskResponse;
import com.yanxin.admin.elastic.repository.TaskExecuteInfoRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.profiles.active=dev"})
public class ItemServiceTest {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private TaskExecuteInfoService taskExecuteInfoService;

    @Autowired
    private TaskExecuteInfoRepository taskExecuteInfoRepository;

    @Test
    public void create() {

        /**
         * job_num = '{}-{}{}'.format(exec_type, str(time.strftime('%Y%m%d%H%M%S', time.localtime(time.time()))),
         *                                   str(time.time()).replace('.', '')[-7:])
         */
        TaskExecuteInfo task = new TaskExecuteInfo();
        // task.set_id("command-202108121853299622339");
        Date date = new Date();
        task.setCreate_date(DateUtil.formatDateTime(date));
        task.setLogs("https://www.baidu.com1");
        task.setJob_num("command-202108121853299622339");
        task.setStatus("SUCCESS");
        List<TaskResponse> responseList = Lists.newArrayList();
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setEnd_date(DateUtil.formatDateTime(new Date()));
        taskResponse.setStart_date(DateUtil.formatDateTime(date));
        taskResponse.setStatus("SUCCESS");
        taskResponse.setResult("https://www.baidu.com1");
        responseList.add(taskResponse);
        task.setResponse(responseList);
        IndexQueryBuilder builder = new IndexQueryBuilder() //
                .withId("command-202108121853299622339") //
                .withObject(task);
        IndexCoordinates indexCoordinates = IndexCoordinates.of("task");
        final String index = elasticsearchOperations.index(builder.build(), indexCoordinates);
        System.out.println(index);
    }

    @Test
    public void findById() {

        final Optional<TaskExecuteInfo> optTask = taskExecuteInfoRepository.findById("command-202108121853299622339");
        if (optTask.isPresent()) {
            final TaskExecuteInfo taskExecuteInfo = optTask.get();
            System.out.println(taskExecuteInfo.toString());
        }
    }
}