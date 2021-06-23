package com.yanxin.admin.service;

import com.yanxin.admin.domain.Role;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2020-12-24 14:18
 */
public interface RoleService {

    Role selectByName(String name);

    Role insertRole(Role role);
}
