package com.yanxin.credit.controller;

import com.yanxin.common.base.ResultBody;
import com.yanxin.common.utils.ResultUtils;
import com.yanxin.credit.entity.Member;
import com.yanxin.credit.service.IMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2022-02-10 17:27
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @GetMapping(value = "/list")
    @ApiOperation("会员列表")
    public ResultBody list(@RequestParam(required = false) String phone) {

        return ResultUtils.success(memberService.selectList(phone));
    }

    @PostMapping(value = "/add")
    @ApiOperation("会员新增")
    public ResultBody add(@RequestBody Member member) {

        return ResultUtils.success(memberService.insertMember(member));
    }

    @PostMapping(value = "/edit")
    @ApiOperation("会员修改")
    public ResultBody edit(@RequestBody Member member) {

        return ResultUtils.success(memberService.updateMember(member));
    }

    @PostMapping(value = "/delete")
    @ApiOperation("会员删除")
    public ResultBody drop(@RequestBody Member member) {

        return ResultUtils.success(memberService.deleteMember(member));
    }

}
