package com.yanxin.admin.singleton;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2022-03-02 20:36
 */
public class LdapTemplateInstance {

    private static volatile LdapTemplateInstance instance;

    private LdapTemplate ldapTemplate;

    /*private LdapTemplateInstance() {
        // Protect against instantiation via reflection
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException("Already initialized.");
        }
    }*/

    /**
     * The instance doesn't get created until the method is called for the first time.
     */
    public static synchronized LdapTemplateInstance getInstance(LdapTemplate ldapTemplate) {
        if (instance == null) {
            synchronized (LdapTemplateInstance.class) {
                if (instance == null) {
                    instance = new LdapTemplateInstance(ldapTemplate);
                }
            }
        }
        return instance;
    }

    public LdapTemplateInstance(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }
}

