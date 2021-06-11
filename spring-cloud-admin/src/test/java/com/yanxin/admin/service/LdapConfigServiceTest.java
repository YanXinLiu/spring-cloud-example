package com.yanxin.admin.service;


import com.yanxin.admin.domain.LdapConfig;
import com.yanxin.admin.event.LdapEvent;
import com.yanxin.admin.util.SpringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.profiles.active=dev"})
public class LdapConfigServiceTest {


    @Test
    public void updateById() {

        SpringUtils.publishEvent(new LdapEvent(LdapConfig.builder()
                .id(2L)
                .username("yanxin").build()));
    }
}