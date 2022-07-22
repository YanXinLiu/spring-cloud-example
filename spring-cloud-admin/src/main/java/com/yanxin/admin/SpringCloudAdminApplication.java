package com.yanxin.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(value = "com.yanxin.admin.service.feign")
@EnableCaching
public class SpringCloudAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAdminApplication.class, args);
    }

}
