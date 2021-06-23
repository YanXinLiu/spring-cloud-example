package com.yanxin.admin.event;

import com.yanxin.admin.domain.LdapConfig;
import com.yanxin.admin.repository.LdapConfigRepository;
import com.yanxin.admin.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
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

    @Async
    @Order
    @EventListener(LdapEvent.class)
    @Transactional(rollbackFor = Exception.class)
    public void refreshLdapTemplate(LdapEvent event) {

        log.info("更新事件通知成功: {}", event.getSource());
        LdapConfig ldapConfig = (LdapConfig) event.getSource();
        ldapConfigRepository.save(ldapConfig);

        DefaultSingletonBeanRegistry beanRegistry = SpringUtils.getBeanRegistry();
        beanRegistry.destroySingleton("contextSource");
        LdapContextSource source = new LdapContextSource();
        source.setBase("cn=ieie");
        source.setUrl("ldap://192.168.1.98");
        source.setPassword("Adadmin@jk888");
        source.setUserDn("administrato1r");
        source.afterPropertiesSet();
        beanRegistry.registerSingleton("contextSource", source);

    }
}
