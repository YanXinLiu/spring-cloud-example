package com.yanxin.admin.annoation;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.lang.annotation.*;

/**
 * @author sa
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Scope("refresh")
@Documented
public @interface Refresh {

    ScopedProxyMode proxyMode() default ScopedProxyMode.TARGET_CLASS;
}
