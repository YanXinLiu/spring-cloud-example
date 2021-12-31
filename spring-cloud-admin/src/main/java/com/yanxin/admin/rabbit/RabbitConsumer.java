package com.yanxin.admin.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-10-09 16:20
 */
@Component
public class RabbitConsumer {

    @RabbitListener(queues = "app_aliyun_yanxin")
    public void process(String message) {

        System.out.println(message.toString());
    }
}
