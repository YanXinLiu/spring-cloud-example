package com.yanxin.admin.service.impl;

import com.yanxin.admin.domain.LdapConfig;
import com.yanxin.admin.event.LdapEvent;
import com.yanxin.admin.repository.LdapConfigRepository;
import com.yanxin.admin.service.LdapConfigService;
import com.yanxin.admin.util.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-06-08 11:35
 */
@Service
public class LdapConfigServiceImpl implements LdapConfigService {

    @Autowired
    private LdapConfigRepository ldapConfigRepository;

    @Override
    public LdapConfig updateById(LdapConfig ldapConfig) {

        ldapConfigRepository.save(ldapConfig);
        // 发布更新事件
        SpringUtils.publishEvent(new LdapEvent(ldapConfig));
        return ldapConfig;
    }
}
