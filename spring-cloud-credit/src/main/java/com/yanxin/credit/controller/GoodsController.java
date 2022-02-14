package com.yanxin.credit.controller;

import com.yanxin.common.base.ResultBody;
import com.yanxin.common.utils.ResultUtils;
import com.yanxin.credit.entity.Goods;
import com.yanxin.credit.entity.Member;
import com.yanxin.credit.service.IGoodsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2022-02-10 10:43
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @GetMapping(value = "/list")
    @ApiOperation("商品列表")
    public ResultBody list(@RequestParam(required = false) String name) {

        return ResultUtils.success(goodsService.selectList(name));
    }

    @PostMapping(value = "/add")
    @ApiOperation("商品新增")
    public ResultBody add(@RequestBody Goods goods) {

        return ResultUtils.success(goodsService.insertGoods(goods));
    }

    @PostMapping(value = "/edit")
    @ApiOperation("商品修改")
    public ResultBody edit(@RequestBody Goods goods) {

        return ResultUtils.success(goodsService.updateGoods(goods));
    }

    @PostMapping(value = "/delete")
    @ApiOperation("商品删除")
    public ResultBody drop(@RequestBody Goods goods) {

        return ResultUtils.success(goodsService.deleteGoods(goods));
    }

}
