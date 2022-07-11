package com.yanxin.credit;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yanxin.**.mapper")
public class SpringCloudCreditApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringCloudCreditApplication.class, args);
    }


}
