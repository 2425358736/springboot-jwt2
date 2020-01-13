package com.telecom.shop.config.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义一个切面
 * @author admin
 */
@Aspect
@Component
public class LogRecordAspect {

    @Autowired
    public HttpServletRequest httpServletRequest;
    @Autowired
    public RedisTemplate redisTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 定义切点Pointcut
     */
    @Pointcut("execution(* com.telecom.shop.*.controller..*.*(..))")
    public void excudeService() {
    }

    /**
     * 环绕通知
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object o = pjp.proceed();
        return o;
    }

    /**
     * 后置通知 log打印
     * @param joinPoint
     * @param monitorLog
     */
    @After("excudeService() && @annotation(monitorLog)")
    public void afterMethod(JoinPoint joinPoint, MonitorLog monitorLog) {

        // 参数
        String parameter = "{}";
        // 访问的接口
        String url = "";
        // 接口描述
        String interfaceDescription = "";
        // 客户端ip
        String ip = "";
        // 客户端请求方式
        String method = "";
        // 客户端浏览器类型
        String browserMethod = "";
        // 客户端操作系统
        String os = "";
        // 访问人id
        Long visitId = null;


        url = httpServletRequest.getRequestURL().toString();
        interfaceDescription = monitorLog.interfaceDescription();
        //取得实际地址。如果使用了匿名代理的话
        ip = httpServletRequest.getHeader("X-Forwarded-For");
        //取不到forwarded地址就取实际的ip地址
        if (StringUtils.isEmpty(ip)) {
            //代理地址
            ip = httpServletRequest.getRemoteAddr();
        } else {
            //如果有多级反向代理，返回的是一组ip，取第一个
            ip = ip.split(",")[0];
        }
        method = httpServletRequest.getMethod();
        String agent = httpServletRequest.getHeader("User-Agent");
        String[] str = agent.split("\\)");
        if (str.length > 0) {
            os = str[0].split("\\(")[1];
        }
        if (str.length >  2) {
            browserMethod = str[2];
        }
        String log = "(" + visitId + ")" + "在ip为"+ip+"的位置上，"+"用一台"+os+"系统的机器上，通过" + browserMethod + "浏览器"
                + "访问了pc" + "端(" + method + ")" + url + "接口，携带了" + parameter + "参数,此接口实现了" + interfaceDescription + "业务";
        logger.info(log);
    }

    /**
     * 前置通知
     * @param joinPoint
     */
    @Before("excudeService()")
    public void beforeMethod(JoinPoint joinPoint) {

    }

    /**
     * 返回通知 导出
     * @param joinpoint
     * @param rvt
     */
    @AfterReturning(pointcut = "excudeService()", returning = "rvt")
    public void afterReturningMethod(JoinPoint joinpoint, Object rvt) {

    }

    /**
     * 异常通知
     * @param joinPoint
     */
    @AfterThrowing("excudeService()")
    public void afterThrowingMethod(JoinPoint joinPoint) {
    }


    /**
     * 请求方式转换
     * @param method
     * @return
     */
    public Integer toMethod(String method) {
        Integer methodInt = 0;
        switch (method) {
            case "POST":
                methodInt = 0;
                break;
            case "GET":
                methodInt = 1;
                break;
            case "PUT":
                methodInt = 2;
                break;
            case "HEAD":
                methodInt = 3;
                break;
            case "DELETE":
                methodInt = 4;
                break;
            case "TRACE":
                methodInt = 5;
                break;
            case "CONNECT":
                methodInt = 6;
                break;
            case "OPIONS":
                methodInt = 7;
                break;
            default:
                methodInt = 8;
                break;
        }
        return methodInt;
    }
}