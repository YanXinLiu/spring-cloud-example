package com.yanxin.admin.controller;

import com.yanxin.admin.domain.User;
import com.yanxin.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2020-09-02 15:09
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/save")
    public User saveUser(@RequestBody User user) {

        return userService.insertUser(user);
    }

    @GetMapping(value = "/{id}/info")
    public User searchUserById(@PathVariable(value = "id") String id) {

        return userService.selectByName(id);
    }

    @GetMapping(value = "/list")
    public List<User> searchUserList() {

        return userService.getAll();
    }
}
