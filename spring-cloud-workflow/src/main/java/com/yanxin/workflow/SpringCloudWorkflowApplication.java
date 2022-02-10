package com.yanxin.workflow;


import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class SpringCloudWorkflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudWorkflowApplication.class, args);
    }

}
