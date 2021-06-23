package com.yanxin.admin.event;

import com.yanxin.admin.domain.LdapConfig;
import org.springframework.context.ApplicationEvent;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-06-08 11:50
 */
public class LdapEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public LdapEvent(LdapConfig source) {
        super(source);
    }
}
