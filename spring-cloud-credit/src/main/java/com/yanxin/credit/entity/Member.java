package com.yanxin.credit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2022-02-10 17:23
 */
@Data
@TableName
public class Member {

    private Integer memberId;

    private String name;

    private String phone;

    private String idCard;

    private BigDecimal credit;

    private String avatar;

    private Integer createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    private Integer updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;
}
