package com.bong.svc;

import com.bong.domain.request.StbInfoSearchParam;
import com.bong.repository.mappers.StbMapper;
import com.bong.domain.response.Stb;
import com.bong.repository.redis.ChannelRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * Created by ibong-gi on 2016. 12. 6..
 */
@Slf4j
@Component("stbService")
public class StbServiceImpl implements StbService {

    @Autowired StbMapper stbMapper;
    @Autowired ChannelRedisRepository channelRedisRepository;
//    @Autowired RedisTemplate redisTemplate;
//    @Autowired RedisTemplate redisSecTemplate;

    @Override
    public String getStbStatus(StbInfoSearchParam stbInfoSearchParam) {
        String result = null;
        String licenseKey = null;
        String licenseInfo = null;
        String infoMacAddr = null;

        try{
            licenseInfo = (String)channelRedisRepository.get(stbInfoSearchParam.getStb_id());

            log.info("licenseeInfo : " + licenseInfo);

            infoMacAddr = licenseInfo.substring(0, licenseInfo.indexOf("#"));
            if(licenseInfo != null && (stbInfoSearchParam.getMac_address().equals(infoMacAddr))){
                result = "0#" + licenseInfo.substring(licenseInfo.indexOf("#")+1);
            }else{

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        log.info("result : " + result);
//        Stb stb = stbMapper.getStbInfo(stbInfoSearchParam);
//        log.info("stb id : " + stb.toString());
//        String key = "{B32A7AAA-859A-11E3-BA55-F9B26550CBD3}";

        return result;
    }

    private String getLicenseInfoDB(StbInfoSearchParam stbInfoSearchParam){
        Stb stb = stbMapper.getStbInfo(stbInfoSearchParam);
        int retType = 0;
        if(!ObjectUtils.isEmpty(stb)){
            String iptvStatusCode   = StringUtils.defaultString(stb.getIptv_status_code(), "");
            String tvpPackage       = StringUtils.defaultString(stb.getTv_package(), "");

            if("".equals(iptvStatusCode)){
                retType = 1;
            }else if("0".equals(iptvStatusCode)){
                retType = 2;
            }else if("1".equals(iptvStatusCode)){
                retType = Integer.parseInt(tvpPackage);
            }else{
                retType = 0;
            }

            String result = null;
            switch(retType){
                case 0 : result = null; break;
                case 1 : result = "1#"; break;
                case 2 : result = "2#"; break;
                default :

                    break;
            }
        }

        return "aa";
    }
}
