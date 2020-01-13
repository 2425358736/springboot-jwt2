package com.telecom.shop.config.annotation;

import java.lang.annotation.*;

/**
 * 开发公司：个人
 * 版权：个人
 * <p>
 * WxAuth
 * 微信拦截
 * @author 刘志强
 * @created Create Time: 2019/7/2
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WxAuth {
    boolean value() default true;
}
