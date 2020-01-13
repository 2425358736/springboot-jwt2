package com.telecom.shop.config.druid;


import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @author admin
 */
@WebFilter(
        filterName = "druidWebStatFilter",
        urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "exclusions", value = "*.js,*.jpg,*.png,*.gif,*.ico,*.css,/druid/*")//配置本过滤器放行的请求后缀
        }
)
public class DruidStatFilter extends WebStatFilter {
}
