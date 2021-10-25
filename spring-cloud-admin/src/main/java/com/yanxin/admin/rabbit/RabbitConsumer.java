package com.yanxin.admin.rabbit;

import com.yanxin.admin.config.RabbitConfiguration;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-10-09 16:20
 */
@Component
@RabbitListener(queues = RabbitConfiguration.QUEUE_A)
public class RabbitConsumer {

    @RabbitHandler
    public void process(Object obj) {
        throw new RuntimeException("重试机制");
        // System.out.println("DirectReceiver消费者收到消息  process_queue_111 : " + obj.toString());
    }
}
