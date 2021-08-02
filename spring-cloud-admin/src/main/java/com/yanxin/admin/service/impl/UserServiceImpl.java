package com.yanxin.admin.service.impl;

import com.yanxin.admin.domain.LdapUser;
import com.yanxin.admin.domain.Role;
import com.yanxin.admin.domain.User;
import com.yanxin.admin.dto.LoginUserDTO;
import com.yanxin.admin.repository.LdapUserRepository;
import com.yanxin.admin.repository.UserRepository;
import com.yanxin.admin.service.RoleService;
import com.yanxin.admin.service.TokenService;
import com.yanxin.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2020-12-24 14:18
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LdapUserRepository ldapUserRepository;

    @Autowired
    private LdapTemplate ldapTemplate;


    @Override
    @Transactional(readOnly = true)
    public User selectByName(String username) {

        List<LdapUser> users = ldapTemplate.find(LdapQueryBuilder.query()
                .where("cn").is("wangwu")
                .or("sAMAccount1Name").is("ww")
                .or("userPrincipalName").is("w1w@jktest.cn"), LdapUser.class);

        /*return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("未找到用户:" + username));*/
        return User.builder()
                .username("ladp").build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User insertUser(User user) {

        return userRepository.save(user);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role insertUserAndRole() {

        User user = insertUser(User.builder().username("yan2")
                .password("1234561")
                .build());

        Role role = roleService.insertRole(Role.builder().name("yan22")
                .description("admin222222")
                .build());

        Role role1 = roleService.selectByName("yan22");
        if (role1 == null) {
            return role1;
        }
        return role1;
    }

    @Override
    public Boolean login(LoginUserDTO loginUserDTO) {

        Optional<LdapUser> opt = ldapUserRepository.findByCnAndUserPrincipalName("wangwu", "ww@jktest.cn");
        LdapUser user = opt.get();
        EqualsFilter filter = new EqualsFilter("sAMAccountName", user.getSAMAccountName());
        return ldapTemplate.authenticate("", filter.toString(), "123qweASD");
    }
}
