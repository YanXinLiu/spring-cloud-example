package com.yanxin.admin.config;

import com.rabbitmq.client.DefaultSaslConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
public class RabbitConfiguration {


    @Autowired
    private ConnectionFactory connectionFactory;

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

    public static final String EXCHANGE_A = "my-mq-exchange_A";

    public static final String QUEUE_A = "QUEUE_B";

    public static final String ROUTING_KEY_A = "spring-boot-routingKey_A";

    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE_A);
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setChannelTransacted(true);
        return template;
    }

    /**
     * 获取队列A
     *
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_A, true);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(ROUTING_KEY_A);
    }

}
