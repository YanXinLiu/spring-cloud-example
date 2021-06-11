package com.yanxin.admin.service;

import com.yanxin.admin.domain.User;
import com.yanxin.admin.dto.LoginUserDTO;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2020-12-24 14:18
 */
public interface UserService {

    User selectByName(String username);

    User insertUser(User user);

    void insertUserAndRole();


    Boolean login(LoginUserDTO loginUserDTO);
}
