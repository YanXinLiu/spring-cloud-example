package com.yanxin.admin.rabbit.work;

import com.rabbitmq.client.Channel;
import com.yanxin.admin.constant.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-10-09 16:20
 */
@Component
@Slf4j
public class RabbitConsumer {

    @Autowired
    private Executor asyncExecutor;

    @RabbitListener(queues = RabbitConstants.WORK_QUEUE_NAME)
    @RabbitHandler
    @Transactional(rollbackFor = Exception.class)
    public void workQueueListenerFirst(Channel channel, Message message) {

        String resData = StringUtils.toEncodedString(message.getBody(), StandardCharsets.UTF_8);
        CompletableFuture.runAsync(() -> {
            MessageProperties messageProperties = message.getMessageProperties();
            log.info("rabbit 队列1 listener first receiver: {}, Message Content: {} " + messageProperties.getMessageId(), resData);
            try {
                channel.basicAck(messageProperties.getDeliveryTag(), true);
            } catch (IOException e) {
                log.error("消息签收异常: {}", e.getMessage());
            }
        }, asyncExecutor);
    }

    @RabbitListener(queues = RabbitConstants.WORK_QUEUE_NAME)
    @RabbitHandler
    @Transactional(rollbackFor = Exception.class)
    public void workQueueListenerSecond(Channel channel, Message message) {

        String resData = StringUtils.toEncodedString(message.getBody(), StandardCharsets.UTF_8);
        CompletableFuture.runAsync(() -> {
            MessageProperties messageProperties = message.getMessageProperties();
            try {
                channel.basicAck(messageProperties.getDeliveryTag(), true);
            } catch (IOException e) {
                log.error("消息签收异常: {}", e.getMessage());
            }
            log.info("rabbit 队列2 listener first receiver: {}, Message Content: {} " + messageProperties.getMessageId(), resData);
        }, asyncExecutor);
    }
}
