package com.telecom.shop.config.druid;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author admin
 * @author admin
 * @ServletComponentScan // 用于扫描所有的Servlet、filter、listener
 */
@Configuration
@ServletComponentScan
public class DruidConfig {
    /**
     * 加载时读取指定的配置信息,前缀为spring.datasource.druid
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}
