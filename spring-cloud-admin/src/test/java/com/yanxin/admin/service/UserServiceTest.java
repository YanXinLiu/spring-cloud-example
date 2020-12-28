package com.yanxin.admin.service;

import com.yanxin.admin.domain.LdapUser;
import com.yanxin.admin.repository.LdapUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.profiles.active=dev"})
public class UserServiceTest {

    @Autowired
    private LdapTemplate ldapTemplate;

    @Autowired
    private LdapUserRepository ldapUserRepository;

    @Test
    public void selectByName() {

        // Optional<LdapUser> opt1 = ldapUserRepository.findByUserPrincipalName("ww@jktest.cn");
        // Optional<LdapUser> opt11 = ldapUserRepository.findBysAMAccountName("ww");
        // List<String> data = ldapTemplate.list("OU=IT,OU=Shanghai,OU=China");
        List<LdapUser> users = ldapTemplate.find(LdapQueryBuilder.query()
                .where("cn").is("wan1gwu")
                .or("sAMAccount1Name").is("ww")
                .or("userPrincipalName").is("w1w@jktest.cn"), LdapUser.class);

        Optional<LdapUser> opt = ldapUserRepository.findByCnAndUserPrincipalName("wangwu", "ww@jktest.cn");
        LdapUser user = opt.get();
        EqualsFilter filter = new EqualsFilter("sAMAccountName", user.getSAMAccountName());
        Assert.assertTrue(ldapTemplate.authenticate("", filter.toString(), "1231qweASD"));

    }
}