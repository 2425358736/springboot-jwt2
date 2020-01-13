package com.telecom.shop.config.auth.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 开发公司：刘志强
 * 版权：刘志强
 * <p>
 * User
 *
 * @author 刘志强
 * @created Create Time: 2019/4/15
 */

public class User implements UserDetails {
    private String userName;
    private String password;

    private List<Auth> list;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Auth> getList() {
        return list;
    }

    public void setList(List<Auth> list) {
        this.list = list;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.list;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.getUserName();
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