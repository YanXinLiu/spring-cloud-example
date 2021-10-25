package com.yanxin.admin.config;

import com.yanxin.admin.properties.ElasticClusterProperties;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;


@Configuration
public class ElasticRestConfiguration extends AbstractElasticsearchConfiguration {


    @Autowired
    private ElasticClusterProperties elasticClusterProperties;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(elasticClusterProperties.getUris().get(0))
                .withBasicAuth(elasticClusterProperties.getUsername(), elasticClusterProperties.getPassword())
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

}
