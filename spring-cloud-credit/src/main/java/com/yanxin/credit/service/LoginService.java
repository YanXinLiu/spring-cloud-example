package com.yanxin.credit.service;

import com.yanxin.credit.dto.LoginDTO;
import com.yanxin.credit.dto.LoginUserDTO;
import com.yanxin.credit.utils.ServletUtils;
import com.yanxin.credit.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2022-02-09 15:55
 */
@Service
public class LoginService {


    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;


    public LoginVO login(LoginDTO login) {

        // 用户验证
        Authentication authentication = null;
        // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
        authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        LoginUserDTO loginUser = null;
        if (authentication.getPrincipal() instanceof LoginUserDTO) {
            loginUser = (LoginUserDTO) authentication.getPrincipal();
        }
        // 生成token
        return LoginVO.builder()
                .token(tokenService.createToken(loginUser))
                .build();
    }
}
