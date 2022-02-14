package com.yanxin.credit.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanxin.credit.entity.Member;
import com.yanxin.credit.mapper.MemberMapper;
import com.yanxin.credit.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2022-02-08 18:38
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public List<Member> selectList(String phone) {

        QueryWrapper<Member> query = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(phone)) {
            query.eq("phone", phone);
        }
        return memberMapper.selectList(query);
    }

    @Override
    public int insertMember(Member member) {

        member.setCreateTime(new Date());
        member.setUpdateTime(new Date());
        // 名称加手机号后四位
        member.setName(member.getName() + StrUtil.subSuf(member.getPhone(), 7));
        return memberMapper.insert(member);
    }

    @Override
    public int deleteMember(Member member) {
        return memberMapper.deleteById(member.getMemberId());
    }

    @Override
    public int updateMember(Member member) {
        member.setUpdateTime(new Date());
        // 名称加手机号后四位
        member.setName(member.getName() + StrUtil.subSuf(member.getPhone(), 7));
        return memberMapper.updateById(member);
    }
}
