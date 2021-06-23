package com.yanxin.admin.service.impl;

import com.yanxin.admin.domain.User;
import com.yanxin.admin.dto.LoginUserDTO;
import com.yanxin.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2020-12-24 14:10
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.selectByName(username);

        return LoginUserDTO.builder()
                .username(user.getUsername())
                .id(user.getId())
                .build();
    }

}
