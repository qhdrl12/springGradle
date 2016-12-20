package com.bong.com;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.*;

/**
 * Created by ibong-gi on 2016. 12. 19..
 */

@Configuration
public class AppConfig {

    @Autowired
    RedisProperty redisProperty;

    private Set<HostAndPort> jedisClusterNodes(){
        Set<HostAndPort> hostAndPorts = new HashSet<>();

        Map<String, Map> hosts = (Map<String, Map>) redisProperty.getRedis().get("hosts");
        for(Map.Entry<String, Map> entry : hosts.entrySet()){
            Map<String, Integer> value = entry.getValue();
            hostAndPorts.add(new HostAndPort(String.valueOf(value.get("host")), value.get("port")));
        }

        return hostAndPorts;
    }

    private GenericObjectPoolConfig initPoolConfiguration(){
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        Map<String, Integer> pool = (Map<String, Integer>) redisProperty.getRedis().get("pool");
        config.setMaxTotal(pool.get("max-active"));
        config.setMaxIdle(pool.get("max-idle"));
        config.setMinIdle(pool.get("min-idle"));
        config.setMaxWaitMillis(pool.get("max-wait"));

        return config;
    }

    @Bean
    public JedisCluster jedisCluster(){
        return new JedisCluster(jedisClusterNodes(), (int)redisProperty.getRedis().get("timeout"), initPoolConfiguration());
    }
}
