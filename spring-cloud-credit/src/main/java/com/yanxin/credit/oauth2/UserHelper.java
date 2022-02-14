package com.yanxin.credit.oauth2;

import com.yanxin.credit.dto.LoginUserDTO;
import com.yanxin.credit.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 认证信息帮助类
 *
 * @author lyx
 */
public class UserHelper {


    /**
     * 获取登录用户认证信息
     *
     * @return
     */
    public static LoginUserDTO getUserAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() != null) {
            if (authentication.getPrincipal() instanceof LoginUserDTO) {
                return (LoginUserDTO) authentication.getPrincipal();
            }
        }
        return null;
    }

    /**
     * 获取登录用户详细资料
     *
     * @return
     */
    public static User getUserProfile() {
        LoginUserDTO userAuth = getUserAuth();
        if (userAuth != null) {
            return userAuth.getUser();
        }
        return null;
    }
}
