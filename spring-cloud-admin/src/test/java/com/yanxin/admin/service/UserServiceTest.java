package com.yanxin.admin.service;

import cn.hutool.core.util.StrUtil;
import com.yanxin.admin.domain.LdapConfig;
import com.yanxin.admin.domain.LdapUser;
import com.yanxin.admin.domain.User;
import com.yanxin.admin.repository.LdapUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.profiles.active=dev"})
public class UserServiceTest {

    @Autowired
    private LdapTemplate ldapTemplate;

    @Autowired
    private LdapUserRepository ldapUserRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private LdapConfigService ldapConfigService;

    @Test
    public void selectByName() {

        List<LdapUser> users = ldapTemplate.find(LdapQueryBuilder.query()
                        .where("cn").is("wangwu")
                        .or("sAMAccount1Name").is("ww")
                        .or("userPrincipalName").is("w1w@jktest.cn")
                , LdapUser.class);

        ldapConfigService.updateById(LdapConfig.builder()
                .id(1L)
                .urls("192.168.3.186").base("CN")
                .username("admin")
                .password("123adgqer")
                .build());

        Optional<LdapUser> opt = ldapUserRepository.findByCnAndUserPrincipalName("wangwu", "ww@jktest.cn");
        LdapUser user = opt.get();
        EqualsFilter filter = new EqualsFilter("sAMAccountName", user.getSAMAccountName());
        Assert.assertTrue(ldapTemplate.authenticate("", filter.toString(), "123qweASD"));

    }

    @Test
    public void listUsers() {
        final List<User> all = userService.getAll();
        Assertions.assertNotNull(all);
    }

    @Test
    public void getUserById() {
    }

    @Test
    public void insertUser() {

        final User yyy = userService.insertUser(User.builder()
                .username("yyy")
                .password("123qweASD")
                .createTime(new Date())
                .build());
        final User userById = userService.getUserById(StrUtil.toString(yyy.getId()));
        Assertions.assertNotNull(userById);
    }
}