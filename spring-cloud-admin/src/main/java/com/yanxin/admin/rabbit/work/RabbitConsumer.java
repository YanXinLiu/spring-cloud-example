package com.yanxin.admin.rabbit.work;

import com.rabbitmq.client.Channel;
import com.yanxin.admin.constant.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-10-09 16:20
 */
@Component
@Slf4j
public class RabbitConsumer {

    @RabbitListener(queues = RabbitConstants.WORK_QUEUE_NAME)
    @RabbitHandler
    @Transactional(rollbackFor = Exception.class)
    @Async
    public void workQueueListenerFirst(Object content, Channel channel, Message message) throws IOException {

        // String resData = StringUtils.toEncodedString(message.getBody(), StandardCharsets.UTF_8);
        MessageProperties messageProperties = message.getMessageProperties();
        channel.basicAck(messageProperties.getDeliveryTag(), true);
        log.info("rabbit workQueue1 listener first receiver: {}, Message Content: {} " + messageProperties.getMessageId(), content);
    }

    @RabbitListener(queues = RabbitConstants.WORK_QUEUE_NAME)
    @RabbitHandler
    @Transactional(rollbackFor = Exception.class)
    @Async
    public void workQueueListenerSecond(Object content, Channel channel, Message message) throws IOException {

        MessageProperties messageProperties = message.getMessageProperties();
        channel.basicAck(messageProperties.getDeliveryTag(), true);
        log.info("rabbit workQueue2 listener first receiver: {}, Message Content: {} " + messageProperties.getMessageId(), content);
    }
}
