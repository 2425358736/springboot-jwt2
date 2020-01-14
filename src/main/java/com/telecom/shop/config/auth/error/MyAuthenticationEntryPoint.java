package com.telecom.shop.config.auth.error;

import com.telecom.shop.config.interceptor.LoginIntercept;
import com.telecom.shop.util.result.AppResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author admin
 */
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        AppResult appResult = AppResult.errorReturn(401, authException.getMessage(),authException.getLocalizedMessage());
        LoginIntercept.writer(request,response,appResult);
    }

}
