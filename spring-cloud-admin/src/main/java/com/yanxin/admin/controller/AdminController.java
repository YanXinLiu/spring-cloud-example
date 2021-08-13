package com.yanxin.admin.controller;

import com.yanxin.admin.domain.LdapConfig;
import com.yanxin.admin.dto.LoginUserDTO;
import com.yanxin.admin.feign.NacosFeignService;
import com.yanxin.admin.service.LdapConfigService;
import com.yanxin.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2020-09-02 15:09
 */
@RestController
public class AdminController {

    @Autowired
    private NacosFeignService nacosFeignService;

    @Autowired
    private UserService userService;

    @Autowired
    private LdapConfigService ldapConfigService;


    @GetMapping(value = "/test/hi")
    public String test() {

        return nacosFeignService.test("Hi Feign");
    }

    @GetMapping(value = "/test/sec")
    public String sec() {

        return "security ok";
    }


    @GetMapping(value = "/updConfig")
    public String updateConfig() {

        userService.selectByName("aaa");
        ldapConfigService.updateById(LdapConfig.builder()
                .id(1L)
                .urls("192.168.3.186").base("CN")
                .username("admin")
                .password("123adgqer")
                .build());

        return "security ok";
    }

    @PostMapping(value = "/login")
    public String getToken(@RequestBody LoginUserDTO loginUserDTO) {

        userService.login(loginUserDTO);
        return "login ok";
    }
}
