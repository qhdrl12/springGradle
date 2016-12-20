package com.bong.com;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by ibong-gi on 2016. 12. 7..
 */

@Component
@ConfigurationProperties(prefix = "spring.database")
public class RedisProperty {

    private Map<String, Object> redis = new HashMap<>();

    public Map<String, Object> getRedis(){
        return redis;
    }
}

