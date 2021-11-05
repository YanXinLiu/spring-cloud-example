package com.yanxin.nacos.configuration;

import com.yanxin.nacos.properties.ElasticClusterProperties;
import com.yanxin.nacos.repository.TaskExecuteInfoRepository;
import com.yanxin.nacos.service.EsRequestService;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@Configuration
@EnableConfigurationProperties(ElasticClusterProperties.class)
@EnableElasticsearchRepositories(basePackages = "com.yanxin")
public class ElasticRestConfiguration extends AbstractElasticsearchConfiguration {


    @Autowired
    private ElasticClusterProperties elasticClusterProperties;


    @Bean
    @ConditionalOnClass(EsRequestService.class)
    public EsRequestService esRequestService() {
        return new EsRequestService();
    }

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
