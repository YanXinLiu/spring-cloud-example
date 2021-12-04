package com.yanxin.admin.config;

import com.rabbitmq.client.DefaultSaslConfig;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-11-11 17:12
 */
@Configuration
public class RabbitConfig {


    @Autowired
    private RabbitProperties rabbitProperties;

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;


    @PostConstruct
    public void init() {
        if (rabbitProperties.getSsl().getEnabled() != null && rabbitProperties.getSsl().getEnabled() && rabbitProperties.getSsl().getKeyStore() != null) {
            cachingConnectionFactory.getRabbitConnectionFactory().setSaslConfig(DefaultSaslConfig.EXTERNAL);
        }
    }
}
