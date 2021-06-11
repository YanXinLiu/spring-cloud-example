package com.yanxin.admin.config;

import com.yanxin.admin.domain.LdapConfig;
import com.yanxin.admin.repository.LdapConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2020-12-25 11:13
 */
@Configuration
@EnableLdapRepositories
public class LdapConfiguration {

    @Autowired
    private LdapConfigRepository ldapConfigRepository;

    @Bean
    public ContextSource contextSource() {

        LdapConfig ldap = ldapConfigRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("未查找到LDAP配置"));
        LdapContextSource source = new LdapContextSource();
        source.setBase(ldap.getBase());
        source.setUrl("ldap://192.168.1.98");
        source.setPassword(ldap.getPassword());
        source.setUserDn(ldap.getUsername());

        return source;
    }

    @Bean
    public LdapTemplate ldapTemplate(ContextSource contextSource) {

        LdapTemplate ldapTemplate = new LdapTemplate(contextSource);
        ldapTemplate.setIgnorePartialResultException(true);
        return ldapTemplate;
    }
}
