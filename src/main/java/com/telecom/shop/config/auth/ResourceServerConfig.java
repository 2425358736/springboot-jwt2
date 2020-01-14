package com.telecom.shop.config.auth;

import com.telecom.shop.config.auth.error.MyAccessDeniedHandler;
import com.telecom.shop.config.auth.error.MyAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 配置资源服务配置
 * @author admin
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        // 所有接口都需要验证访问
        httpSecurity
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
    @Override
    public void configure(ResourceServerSecurityConfigurer resourceServerSecurityConfigurer) throws Exception {
        resourceServerSecurityConfigurer.tokenStore(tokenStore);
        //自定义资源访问认证异常，没有token，或token错误，使用MyAuthenticationEntryPoint
        resourceServerSecurityConfigurer.authenticationEntryPoint(new MyAuthenticationEntryPoint());
        resourceServerSecurityConfigurer.accessDeniedHandler(new MyAccessDeniedHandler());
    }
}
