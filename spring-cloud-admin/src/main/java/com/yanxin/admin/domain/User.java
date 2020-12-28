package com.yanxin.admin.domain;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 性别 0 女，1 男
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 出生年月
     */
    @Temporal(TemporalType.DATE)
    private Date birthday;

    /**
     * 状态 0 无效 1 有效
     */
    private Integer status;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    @Tolerate
    public User() {

    }
}
