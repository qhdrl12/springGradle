package com.bong.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.*;

/**
 * Created by ibong-gi on 2016. 12. 19..
 */

@Configuration
public class AppConfig {

    @Autowired RedisConfig redisConfig;

    @Bean
    public Set<HostAndPort> hostAndPortSet(){
        Set<HostAndPort> hostAndPorts = new HashSet<>();

        Map<String, Map> hosts = (Map<String, Map>)redisConfig.getRedis().get("hosts");

        for(Map.Entry<String, Map> entry : hosts.entrySet()){
            Map<String, Integer> value = entry.getValue();
            hostAndPorts.add(new HostAndPort(String.valueOf(value.get("host")), value.get("port")));
        }

        return hostAndPorts;
    }

    @Bean
    public JedisCluster jedisCluster(){
        return new JedisCluster(hostAndPortSet());
    }

//    public RedisConnectionFactory connectionFactory(){
//        return new JedisConnectionFactory(
//                new RedisClusterConfiguration(redisConfig.getNodes())
//        );
//    }
}
