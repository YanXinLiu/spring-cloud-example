package com.yanxin.admin.rabbit;

import com.yanxin.admin.constant.RabbitConstants;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-10-09 16:21
 */
@Component
public class RabbitProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String sendWorkMessage() {

        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>(8);
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        // 将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        // 把消息放入ROUTING_KEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(RabbitConstants.WORK_QUEUE_NAME, map, correlationId);
        return "ok";
    }

}
