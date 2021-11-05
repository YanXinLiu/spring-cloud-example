package com.yanxin.nacos.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@ConfigurationProperties(prefix = "spring.elasticsearch.rest")
@Data
public class ElasticClusterProperties {

    private List<String> uris;

    private String username;

    private String password;
}
