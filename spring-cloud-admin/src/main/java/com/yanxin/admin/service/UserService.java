package com.yanxin.admin.service;

import com.yanxin.admin.domain.User;
import com.yanxin.admin.dto.LoginUserDTO;

import java.util.List;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2020-12-24 14:18
 */
public interface UserService {

    User selectByName(String username);

    User getUserById(String id);

    List<User> getAll();

    User insertUser(User user);

    Boolean login(LoginUserDTO loginUserDTO);
}
