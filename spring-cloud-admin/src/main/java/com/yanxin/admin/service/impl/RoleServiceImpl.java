package com.yanxin.admin.service.impl;

import com.yanxin.admin.domain.Role;
import com.yanxin.admin.repository.RoleRepository;
import com.yanxin.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-06-01 11:43
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    @Transactional(readOnly = true)
    public Role selectByName(String name) {

        return roleRepository.findByName(name).orElseThrow(() ->
                new IllegalArgumentException("user not found"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role insertRole(Role role) {
        return roleRepository.save(role);
    }

}
