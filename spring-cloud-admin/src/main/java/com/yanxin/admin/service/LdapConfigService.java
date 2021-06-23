package com.yanxin.admin.service;

import com.yanxin.admin.domain.LdapConfig;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2020-12-24 14:18
 */
public interface LdapConfigService {

    LdapConfig updateById(LdapConfig ldapConfig);

}
