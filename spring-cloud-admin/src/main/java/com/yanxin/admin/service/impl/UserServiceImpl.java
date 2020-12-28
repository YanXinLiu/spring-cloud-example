package com.yanxin.admin.service.impl;

import com.yanxin.admin.domain.User;
import com.yanxin.admin.dto.LoginUserDTO;
import com.yanxin.admin.repository.UserRepository;
import com.yanxin.admin.service.TokenService;
import com.yanxin.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private TokenService tokenService;

    @Override
    public User selectByName(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("未找到用户:" + username));
    }

    @Override
    public LoginUserDTO login(LoginUserDTO loginUserDTO) {

        tokenService.createToken(loginUserDTO);
        return null;
    }
}
