package com.yanxin.admin.rabbit;

import com.yanxin.admin.constant.RabbitConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitWorkProvider {

    @Bean
    public Queue workQueue() {
        return new Queue(RabbitConstants.WORK_QUEUE_NAME);
    }
}
