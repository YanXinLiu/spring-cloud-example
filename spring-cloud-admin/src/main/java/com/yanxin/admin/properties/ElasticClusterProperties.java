package com.yanxin.admin.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-09-22 18:02
 */
@Component
@ConfigurationProperties(prefix = "spring.elasticsearch.rest")
@Data
public class ElasticClusterProperties {

    private List<String> uris;

    private String username;

    private String password;
}
