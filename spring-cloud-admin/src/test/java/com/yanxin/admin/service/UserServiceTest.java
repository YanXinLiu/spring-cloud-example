package com.yanxin.admin.service;

import com.yanxin.admin.domain.LdapConfig;
import com.yanxin.admin.domain.LdapUser;
import com.yanxin.admin.domain.Role;
import com.yanxin.admin.domain.User;
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
                .or("userPrincipalName").is("w1w@jktest.cn"), LdapUser.class);

        ldapConfigService.updateById(LdapConfig.builder()
                .id(1L)
                .urls("192.168.3.186").base("CN")
                .username("admin")
                .password("123adgqer")
                .build());

        List<LdapUser> users1 = ldapTemplate.find(LdapQueryBuilder.query()
                .where("cn").is("wangwu")
                .or("sAMAccount1Name").is("ww")
                .or("userPrincipalName").is("w1w@jktest.cn"), LdapUser.class);

        Optional<LdapUser> opt = ldapUserRepository.findByCnAndUserPrincipalName("wangwu", "ww@jktest.cn");
        LdapUser user = opt.get();
        EqualsFilter filter = new EqualsFilter("sAMAccountName", user.getSAMAccountName());
        Assert.assertTrue(ldapTemplate.authenticate("", filter.toString(), "123qweASD"));

    }

    @Test
    public void testRollBack() {

        User user = userService.insertUser(User.builder().username("yan")
                .password("123456")
                .build());

        Role role = roleService.insertRole(Role.builder().name("yan")
                .description("admin")
                .build());

    }
}