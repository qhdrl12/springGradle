package com.bong.svc;

import com.bong.repository.mappers.StbMapper;
import com.bong.domain.Stb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * Created by ibong-gi on 2016. 12. 6..
 */
@Component("stbService")
public class StbServiceImpl implements StbService {

    static final Logger log = LoggerFactory.getLogger(StbServiceImpl.class);

    @Autowired StbMapper stbMapper;
    @Autowired StringRedisTemplate template;

    @Override
    public Stb getTestStb() {
        Stb stb = stbMapper.selectTestStb();
        log.info("stb id : " + stb.getStb_id());

        ValueOperations<String, String> ops = this.template.opsForValue();
        String key = "{B32A7AAA-859A-11E3-BA55-F9B26550CBD3}";

        log.info("redis log = " + ops.get(key));

        return stb;
    }
}
