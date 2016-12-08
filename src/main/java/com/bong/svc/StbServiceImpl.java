package com.bong.svc;

import com.bong.domain.request.StbInfoSearchParam;
import com.bong.repository.mappers.StbMapper;
import com.bong.domain.response.Stb;
import com.bong.repository.redis.ChannelRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by ibong-gi on 2016. 12. 6..
 */
@Slf4j
@Component("stbService")
public class StbServiceImpl implements StbService {

    @Autowired StbMapper stbMapper;
    @Autowired RedisTemplate redisTemplate;
    @Autowired RedisTemplate redisSecTemplate;

    @Override
    public String getStbStatus(StbInfoSearchParam stbInfoSearchParam) {
        Stb stb = stbMapper.getStbInfo(stbInfoSearchParam);
        log.info("stb id : " + stb.toString());

        ChannelRedisRepository redisRepository = new ChannelRedisRepository(redisTemplate);
        String key = "{B32A7AAA-859A-11E3-BA55-F9B26550CBD3}";

        return (String)redisRepository.get(key);
    }
}
