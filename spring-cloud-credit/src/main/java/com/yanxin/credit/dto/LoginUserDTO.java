package com.yanxin.credit.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yanxin.credit.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2020-12-24 11:55
 */
@Data
public class LoginUserDTO implements UserDetails {

    private Long id;

    private String token;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 邮箱
     */
    private Long expireTime;

    /**
     * 用户信息
     */
    private User user;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    public LoginUserDTO() {
    }

    public LoginUserDTO(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getPassword()
    {
        return user.getPassword();
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
