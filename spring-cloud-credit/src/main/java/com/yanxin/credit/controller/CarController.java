package com.yanxin.credit.controller;

import com.yanxin.common.base.ResultBody;
import com.yanxin.common.utils.ResultUtils;
import com.yanxin.credit.dto.CarDTO;
import com.yanxin.credit.service.ICarService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2022-02-10 10:43
 */
@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final ICarService carService;

    @GetMapping(value = "/list")
    @ApiOperation("商品列表")
    public ResultBody list() {

        return ResultUtils.success(carService);
    }

    @PostMapping(value = "/add")
    @ApiOperation("报废车新增/修改")
    public ResultBody add(@RequestBody CarDTO carDTO) {

        return ResultUtils.success(carService.insertCar(carDTO));
    }

}
