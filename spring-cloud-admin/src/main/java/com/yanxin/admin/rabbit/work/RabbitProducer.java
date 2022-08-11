package com.yanxin.admin.rabbit.work;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yanxin.admin.constant.RabbitConstants;
import com.yanxin.admin.dto.GoodsDTO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
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

        GoodsDTO goods = new GoodsDTO();
        goods.setGoodsId(1);
        goods.setImageUrl("http://");
        String messageId = String.valueOf(UUID.randomUUID());
        // CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        // 把消息放入ROUTING_KEY_A对应的队列当中去，对应的是队列A
        Message message = MessageBuilder.withBody(JSONObject.toJSON(goods).toString().getBytes())
                .setMessageId(messageId).build();
        rabbitTemplate.convertAndSend(RabbitConstants.WORK_QUEUE_NAME, message);
        return "ok";
    }

}
