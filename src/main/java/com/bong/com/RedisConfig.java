package com.bong.com;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by ibong-gi on 2016. 12. 7..
 */
@Configuration
public class RedisConfig {



    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    JedisConnectionFactory jedisConnectionFactory(){
        return new JedisConnectionFactory();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.redis-slave")
    JedisConnectionFactory jedisSecConnectionFactory(){
        return new JedisConnectionFactory();
    }

    @Bean(name = "redisTemplate")
    @Qualifier(value = "redisTemplate")
    public RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer(Object.class));
        redisTemplate.setValueSerializer(new GenericToStringSerializer(Object.class));
//        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

    @Bean(name = "redisSecTemplate")
    @Qualifier(value = "redisSecTemplate")
    public RedisTemplate redisSecTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisSecConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer(Object.class));
        redisTemplate.setValueSerializer(new GenericToStringSerializer(Object.class));
        return redisTemplate;
    }

    @Bean
    RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
    return redisCacheManager;
    }

    @Bean
    RedisCacheManager cacheSecManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisSecTemplate());
        return redisCacheManager;
    }
}
