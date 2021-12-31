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

    /**
     * 创建用户，同时使用新的返回值的替换缓存中的值
     * 创建用户后会将allUsersCache缓存全部清空
     * 缓存键值对 value::key
     * 不填value value::default key
     */
    User insertUser(User user);

    Boolean login(LoginUserDTO loginUserDTO);
}
