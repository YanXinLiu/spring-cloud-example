package com.yanxin.admin.config;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.ldap.urls}")
    private String ldapUrl;
    @Value("${spring.ldap.username}")
    private String userName;
    @Value("${spring.ldap.password}")
    private String passWord;
    @Value("${spring.ldap.base}")
    private String base;


    @Bean
    ContextSource contextSource() {

        LdapContextSource source = new LdapContextSource();
        source.setBase(base);
        source.setUrl(ldapUrl);
        source.setPassword(passWord);
        source.setUserDn(userName);
        return source;
    }

    @Bean
    LdapTemplate ldapTemplate(ContextSource contextSource) {

        LdapTemplate ldapTemplate = new LdapTemplate(contextSource);
        ldapTemplate.setIgnorePartialResultException(true);
        return ldapTemplate;
    }
}
