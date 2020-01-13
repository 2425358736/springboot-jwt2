package com.telecom.shop.config.auth.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * 开发公司：刘志强
 * 版权：刘志强
 * <p>
 * Permission
 *权限
 * @author 刘志强
 * @created Create Time: 2019/4/15
 */
@Data
public class Auth implements GrantedAuthority {

    public Auth(String authority){
        this.authority = authority;
    }
    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}