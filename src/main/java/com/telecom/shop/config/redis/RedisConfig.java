package com.telecom.shop.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author admin
 */
@Configuration
public class RedisConfig {

    @Autowired
    RedisTemplate redisTemplate;


    @Bean(name = "redisTemplate" )
    public RedisTemplate<?, ?> getRedisTemplate() {
        /**
         * key序列化类型
         */
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        /**
         * value序列化类型
         */
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}