package com.yanxin.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2020-12-24 11:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserDTO implements UserDetails {

    private Long id;

    private String token;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 邮箱
     */
    private Long expireTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
