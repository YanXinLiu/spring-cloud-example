package com.yanxin.credit.controller;

import com.yanxin.common.base.ResultBody;
import com.yanxin.common.utils.ResultUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2022-02-10 10:43
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @GetMapping(value = "/list")
    @ApiOperation("商品列表")
    public ResultBody list() {

        List<String> list = new ArrayList<>();
        list.add("car");
        list.add("bus");
        return ResultUtils.success(list);
    }

}
