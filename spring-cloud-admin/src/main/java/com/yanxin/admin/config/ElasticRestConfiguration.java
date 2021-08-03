package com.yanxin.admin.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @Program spring-cloud-example
 * @Description: elastic 配置
 * @Author: LiuYanXin
 * @Create: 2020-12-29 15:50
 */
@Configuration
public class ElasticRestConfiguration extends AbstractElasticsearchConfiguration {

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("http://192.168.3.183:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

}
