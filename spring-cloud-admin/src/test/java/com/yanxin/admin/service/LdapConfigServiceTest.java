package com.yanxin.admin.service;


import com.yanxin.admin.domain.LdapConfig;
import com.yanxin.admin.domain.LdapUser;
import com.yanxin.admin.event.LdapEvent;
import com.yanxin.admin.singleton.LdapTemplateInstance;
import com.yanxin.admin.util.SpringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.profiles.active=dev"})
public class LdapConfigServiceTest {


    @Test
    public void updateById() {

        SpringUtils.publishEvent(new LdapEvent(LdapConfig.builder()
                .id(2L)
                .username("yanxin").build()));
    }

    @Test
    public void ldapInstanceTest() {

        LdapContextSource source = new LdapContextSource();
        source.setBase("DC=jktest,DC=cn");
        source.setUrl("ldap://192.168.1.98");
        source.setPassword("Adadmin@jk888");
        source.setUserDn("administrator");
        LdapTemplate ldapTemplate = new LdapTemplate();
        ldapTemplate.setContextSource(source);


        final LdapTemplateInstance instance = LdapTemplateInstance.getInstance(ldapTemplate);

        List<LdapUser> users = ldapTemplate.find(LdapQueryBuilder.query()
                .where("cn").is("wangwu")
                .or("sAMAccount1Name").is("ww")
                .or("userPrincipalName").is("w1w@jktest.cn"), LdapUser.class);
        System.out.println(users);

    }

    @Test
    public void subListTest() {

        List<LdapUser> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            LdapUser ldapUser = new LdapUser();
            ldapUser.setCn("cd");
            ldapUser.setCompany(i + "");
            list.add(ldapUser);
        }
        List<String> groupIds = list.subList(0, 4).stream().map(LdapUser::getCn).collect(Collectors.toList());
        System.out.println(groupIds);
    }
}