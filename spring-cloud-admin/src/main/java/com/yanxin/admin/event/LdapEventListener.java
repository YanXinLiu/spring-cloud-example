package com.yanxin.admin.event;

import com.yanxin.admin.domain.LdapConfig;
import com.yanxin.admin.repository.LdapConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-06-08 13:43
 */
@Slf4j
@Configuration
public class LdapEventListener {

    @Autowired
    private LdapConfigRepository ldapConfigRepository;

    @Autowired
    private DefaultListableBeanFactory defaultListableBeanFactory;


    @Async
    @Order
    @EventListener(LdapEvent.class)
    @Transactional(rollbackFor = Exception.class)
    public void refreshLdapTemplate(LdapEvent event) {

        log.info("更新事件通知成功: {}", event.getSource());
        LdapConfig ldapConfig = (LdapConfig) event.getSource();
        ldapConfigRepository.save(ldapConfig);
    }
}
