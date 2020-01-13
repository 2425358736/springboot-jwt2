package com.telecom.shop.config.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author JJJ
 * @date 2017/2/22
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MonitorLog {
    /**
     * 接口类型 0 查询 1 添加 2 修改 3 删除 4 其他
     * @return
     */
    int interfaceType() default 0;

    String interfaceDescription() default "";
}