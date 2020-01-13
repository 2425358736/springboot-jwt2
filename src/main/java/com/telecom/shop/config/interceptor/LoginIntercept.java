package com.telecom.shop.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.telecom.shop.config.annotation.WxAuth;
import com.telecom.shop.config.enumcom.GlobalAttribute;
import com.telecom.shop.util.result.AppResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author admin
 */
@Component
@Slf4j
public class LoginIntercept implements HandlerInterceptor {
    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 控制单端登录
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        WxAuth wxAuth = null;

        if(o instanceof HandlerMethod) {
            wxAuth = ((HandlerMethod)o).getMethodAnnotation(WxAuth.class);
            if(wxAuth == null) {
                wxAuth = ((HandlerMethod)o).getBeanType().getAnnotation(WxAuth.class);
            }
        }
        if(wxAuth == null || !wxAuth.value()) {
            return true;
        }

        String method = httpServletRequest.getMethod();
        if (StringUtils.equals("OPTIONS", method)) {
            return true;
        }

        String token = httpServletRequest.getHeader("Authorization");
        if (StringUtils.isNotEmpty(token)) {
            try {
                String phone = (String) redisTemplate.opsForValue().get(GlobalAttribute.PhoneToken.getValue() + token);
                if (StringUtils.isEmpty(phone)) {
                    emptyToken(token);
                    return writer(httpServletRequest,httpServletResponse, AppResult.errorReturn(120,"token已失效"));
                } else {
                    return true;
                }
            } catch (Exception e) {
                emptyToken(token);
                return writer(httpServletRequest,httpServletResponse, AppResult.errorReturn(120,"token异常"));
            }
        } else {
            return writer(httpServletRequest,httpServletResponse, AppResult.errorReturn(120,"未携带令牌"));
        }
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public static boolean writer(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AppResult appResult){
        PrintWriter writer = null;
        String originHeader = httpServletRequest.getHeader("Origin");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", originHeader);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.addHeader("Vary", "Origin");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try {
            JSONObject jsonObject = (JSONObject) JSON.toJSON(appResult);
            writer = httpServletResponse.getWriter();
            writer.append(jsonObject.toString());
        } catch (IOException e) {
            System.out.println("response error" + e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return false;
    }

    public void emptyToken(String token) {
        try {
            String phone = (String) redisTemplate.opsForValue().get(GlobalAttribute.PhoneToken.getValue() + token);
            redisTemplate.delete(GlobalAttribute.WxToken.getValue() + phone);
        } catch (Exception e) {}
        redisTemplate.delete(GlobalAttribute.UserIdToken.getValue() + token);
        redisTemplate.delete(GlobalAttribute.PhoneToken.getValue() + token);
    }
}
