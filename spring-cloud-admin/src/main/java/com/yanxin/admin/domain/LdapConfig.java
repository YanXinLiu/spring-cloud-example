package com.yanxin.admin.domain;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-06-08 10:32
 */
@Entity
@Data
@Builder
public class LdapConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String urls;

    private String base;

    private String username;

    private String password;


    @Tolerate
    public LdapConfig() {

    }
}
