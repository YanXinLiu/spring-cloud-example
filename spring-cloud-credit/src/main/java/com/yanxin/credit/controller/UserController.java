package com.yanxin.credit.controller;

import com.yanxin.common.base.ResultBody;
import com.yanxin.common.utils.ResultUtils;
import com.yanxin.credit.service.IUserService;
import com.yanxin.credit.service.TokenService;
import com.yanxin.credit.utils.ServletUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private TokenService tokenService;

    @GetMapping(value = "/info")
    @ApiOperation(value = "获取用户详情接口")
    public ResultBody userInfo() {

        return ResultUtils.success(tokenService.getLoginUser(ServletUtils.getRequest()));
    }

}
