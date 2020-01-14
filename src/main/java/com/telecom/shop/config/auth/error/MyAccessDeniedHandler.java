package com.telecom.shop.config.auth.error;

import com.telecom.shop.config.interceptor.LoginIntercept;
import com.telecom.shop.util.result.AppResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author admin
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        AppResult appResult = AppResult.errorReturn(401, accessDeniedException.getMessage(),accessDeniedException.getLocalizedMessage());
        LoginIntercept.writer(request,response,appResult);
    }
}
