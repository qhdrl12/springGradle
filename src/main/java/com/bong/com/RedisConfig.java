package com.bong.com;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisClusterConfiguration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericToStringSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import redis.clients.jedis.


/**
 * Created by ibong-gi on 2016. 12. 7..
 */

@Data
@Component
@ConfigurationProperties(prefix = "spring")
public class RedisConfig {

    Map<String, Object> redis = new HashMap<>();

//    List<String> nodes;

//    @Bean
//    @ConfigurationProperties(prefix = "spring.redis")
//    public RedisConnectionFactory connectionFactory(){
//        return new JedisConnectionFactory(
//                new RedisClusterConfiguration());
//    }
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
//        return new PropertySourcesPlaceholderConfigurer();
//    }
//
//    @Primary
//    @Bean
//    @ConfigurationProperties(prefix = "spring.redis")
//    JedisConnectionFactory jedisConnectionFactory(){
//        return new JedisConnectionFactory();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.redis-slave")
//    JedisConnectionFactory jedisSecConnectionFactory(){
//        return new JedisConnectionFactory();
//    }
//
//    @Bean(name = "redisTemplate")
//    @Qualifier(value = "redisTemplate")
//    public RedisTemplate redisTemplate(){
//        RedisTemplate redisTemplate = new RedisTemplate();
//        System.out.println("connection Factory : " + connectionFactory());
//        redisTemplate.setConnectionFactory(connectionFactory());
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(new GenericToStringSerializer(Object.class));
//        redisTemplate.setValueSerializer(new GenericToStringSerializer(Object.class));
//        return redisTemplate;
//    }

//    @Bean(name = "redisSecTemplate")
//    @Qualifier(value = "redisSecTemplate")
//    public RedisTemplate redisSecTemplate(){
//        RedisTemplate redisTemplate = new RedisTemplate();
//        redisTemplate.setConnectionFactory(jedisSecConnectionFactory());
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(new GenericToStringSerializer(Object.class));
//        redisTemplate.setValueSerializer(new GenericToStringSerializer(Object.class));
//        return redisTemplate;
//    }
//
//    @Bean
//    RedisCacheManager cacheManager(){
//        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
//    return redisCacheManager;
//    }
//
//    @Bean
//    RedisCacheManager cacheSecManager(){
//        RedisCacheManager redisCacheManager = new RedisCacheManager(redisSecTemplate());
//        return redisCacheManager;
//    }
}
