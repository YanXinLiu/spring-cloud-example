package com.yanxin.credit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanxin.credit.entity.Member;

import java.util.List;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2022-02-08 18:33
 */
public interface IMemberService extends IService<Member> {

    /**
     * 查询列表
     *
     * @param phone 手机
     * @return 会员列表
     */
    public List<Member> selectList(String phone);

    public int insertMember(Member member);

    public int deleteMember(Member member);

    public int updateMember(Member member);

}
