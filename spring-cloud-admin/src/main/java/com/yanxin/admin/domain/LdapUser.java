package com.yanxin.admin.domain;

import lombok.Data;
import org.springframework.ldap.odm.annotations.*;

import javax.naming.Name;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2020-12-24 16:42
 */
@Entry(objectClasses = {"user", "organizationalPerson", "person", "top"}, base = "OU=IT,OU=Shanghai,OU=China")
@Data
public class LdapUser {

    @Id
    private Name dn;

    /**
     * {@link DnAttribute} 查询返回的数据DN列 返回第n个元素值
     */
    @Attribute(name = "cn")
    private String cn;

    @Attribute(name = "sAMAccountName")
    private String sAMAccountName;

    @Attribute(name = "firstName")
    private String firstName;

    @Attribute(name = "userPrincipalName")
    private String userPrincipalName;

    @DnAttribute(value = "ou", index = 0)
    @Transient
    private String company;

    @Transient
    private String someUnmappedField;
}
