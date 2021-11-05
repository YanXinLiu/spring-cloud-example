//package com.yanxin.admin.config;
//
//import com.rabbitmq.client.DefaultSaslConfig;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.retry.backoff.ExponentialBackOffPolicy;
//import org.springframework.retry.policy.SimpleRetryPolicy;
//import org.springframework.retry.support.RetryTemplate;
//
//
//
//@Configuration
//public class RabbitConfiguration {
//
//
//    @Autowired
//    private ConnectionFactory connectionFactory;
//
//
//    public static final String EXCHANGE_A = "my-mq-exchange_A";
//
//    public static final String QUEUE_A = "QUEUE_YY";
//
//    public static final String ROUTING_KEY_A = "spring-boot-routingKey_A";
//
//
//    @Bean
//    public DirectExchange defaultExchange() {
//        return new DirectExchange(EXCHANGE_A);
//    }
//
//
//    @Bean
//    @ConditionalOnProperty(prefix = "spring.rabbitmq.ssl", name = "enabled", havingValue = "true")
//    public CachingConnectionFactory rabbitConnectionFactory(RabbitProperties config)
//            throws Exception {
//        RabbitConnectionFactoryBean factory = new RabbitConnectionFactoryBean();
//        factory.setHost(config.getHost());
//        factory.setPort(config.getPort());
//        factory.setUsername(config.getUsername());
//        factory.setPassword(config.getPassword());
//        if (config.getVirtualHost() != null) {
//            factory.setVirtualHost(config.getVirtualHost());
//        }
//
//        RabbitProperties.Ssl ssl = config.getSsl();
//        if (ssl.getEnabled()) {
//            factory.setUseSSL(true);
//            factory.setKeyStore(ssl.getKeyStore());
//            factory.setKeyStoreType(ssl.getKeyStoreType());
//            // 没有值也需要设置
//            factory.setKeyStorePassphrase("");
//            factory.setTrustStore(ssl.getTrustStore());
//            factory.setTrustStoreType(ssl.getTrustStoreType());
//            factory.setTrustStorePassphrase(ssl.getTrustStorePassword());
//            factory.setSaslConfig(DefaultSaslConfig.EXTERNAL);
//            factory.setSslAlgorithm(ssl.getAlgorithm());
//            factory.setEnableHostnameVerification(false);
//            factory.setSkipServerCertificateValidation(true);
//        }
//        factory.afterPropertiesSet();
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(
//                factory.getObject());
//        return connectionFactory;
//    }
//
//    /**
//     * 监听策略
//     * @param config rabbit properties
//     * @return
//     */
//    @Bean
//    @ConditionalOnProperty(prefix = "spring.rabbitmq.listener", name = "simple.retry.enabled", havingValue = "true")
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(RabbitProperties config) {
//
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        factory.setPrefetchCount(1);
//        factory.setRetryTemplate(rabbitRetryTemplate());
//
//        return factory;
//    }
//
//
//    /**
//     * 重试机制
//     * @return
//     */
//    @Bean
//    public RetryTemplate rabbitRetryTemplate() {
//        RetryTemplate retryTemplate = new RetryTemplate();
//        retryTemplate.setRetryPolicy(retryPolicy());
//        retryTemplate.setBackOffPolicy(backOffPolicy());
//        return retryTemplate;
//    }
//
//    /**
//     * 设置重试间隔
//     * @return
//     */
//    @Bean
//    public ExponentialBackOffPolicy backOffPolicy() {
//
//        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
//        backOffPolicy.setInitialInterval(3000);
//        return backOffPolicy;
//    }
//
//    /**
//     * 设置重试次数
//     * @return
//     */
//    @Bean
//    public SimpleRetryPolicy retryPolicy() {
//
//        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
//        simpleRetryPolicy.setMaxAttempts(2);
//        return simpleRetryPolicy;
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        RabbitTemplate template = new RabbitTemplate(this.connectionFactory);
//        template.setChannelTransacted(true);
//        return template;
//    }
//
//    /**
//     * 获取队列A
//     *
//     * @return
//     */
//    @Bean
//    public Queue queue() {
//        return new Queue(QUEUE_A, true);
//    }
//
//    @Bean
//    public Binding binding() {
//        return BindingBuilder.bind(queue()).to(defaultExchange()).with(ROUTING_KEY_A);
//    }
//
//}
