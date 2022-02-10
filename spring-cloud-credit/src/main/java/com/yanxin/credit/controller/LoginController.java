package com.yanxin.credit.controller;

import com.yanxin.common.base.ResultBody;
import com.yanxin.common.utils.ResultUtils;
import com.yanxin.credit.dto.LoginDTO;
import com.yanxin.credit.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2022-02-09 15:02
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录接口")
    public ResultBody login(@RequestBody LoginDTO loginDTO) {

        return ResultUtils.success(loginService.login(loginDTO));
    }

}
