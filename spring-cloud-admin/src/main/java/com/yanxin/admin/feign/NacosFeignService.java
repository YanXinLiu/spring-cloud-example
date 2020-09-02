package com.yanxin.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program spring-cloud-example
 * @description: feign服务
 * @author: LiuYanXin
 * @create: 2020-09-02 15:04
 */
@FeignClient(value = "cloud-nacos")
public interface NacosFeignService {
    
    @GetMapping(value = "/test/{message}")
    String test(@PathVariable("message") String message);
}
