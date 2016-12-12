package com.bong.svc;

import com.bong.domain.request.StbInfoSearchParam;
import com.bong.domain.response.LicenseInfo;
import com.bong.domain.response.LicenseResponse;
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
    public LicenseResponse getStbStatus(StbInfoSearchParam stbInfoSearchParam) {
//        String result = null;
        LicenseResponse licenseResponse = new LicenseResponse();
        try{
            String licenseKey;
            String licenseInfo;
            String access;
            String result;
            String[] licenseInfoArr;

            licenseInfo = (String)channelRedisRepository.get(stbInfoSearchParam.getStb_id());

            if(licenseInfo != null && (stbInfoSearchParam.getMac_address().equals(licenseInfo.substring(0, licenseInfo.indexOf("#"))))){
                access = "0";
                result = "YES";
                licenseKey = licenseInfo.substring(licenseInfo.indexOf("#")+1);
            }else{
                licenseKey = getLicenseInfoDB(stbInfoSearchParam);
                access = "1";
                if(StringUtils.isEmpty(licenseKey)) {
                    result = "FAIL";
                }else{
                    licenseInfoArr = licenseKey.split("#");
                    if(2 < Integer.parseInt(licenseInfoArr[0]) && licenseInfoArr[1].indexOf("err") == -1){
                        channelRedisRepository.set(stbInfoSearchParam.getStb_id(), stbInfoSearchParam.getMac_address() + "#" + licenseKey);
                    } //TODO Default Channel 추가
                    result = "YES";
                }
            }
            licenseResponse.setAccess(access);
            licenseResponse.setResult(result);
            licenseResponse.setCh_data(licenseKey);
        }catch(Exception e){
            licenseResponse.setResult("FAIL");
            licenseResponse.setAccess("0");
            licenseResponse.setCh_data("");
            log.error("Get Error [ stb_id : " + stbInfoSearchParam.getStb_id() + " ]" + e);
        }
        log.info("result : " + licenseResponse.toString());

        return licenseResponse;
    }

    private String getLicenseInfoDB(StbInfoSearchParam stbInfoSearchParam){
        Stb stb = stbMapper.getStbInfo(stbInfoSearchParam);
        String result = null;

        if(!ObjectUtils.isEmpty(stb)){
            String iptvStatusCode   = StringUtils.defaultString(stb.getIptv_status_code(), "");
            String tvPackage       = StringUtils.defaultString(stb.getTv_package(), "");
            if("".equals(iptvStatusCode)){ //
                result = "1#";
            }else if("0".equals(iptvStatusCode)){ //
                result = "2#";
            }else if("1".equals(iptvStatusCode)){
                LicenseInfo licenseInfo = stbMapper.getLicenseInfo(stbInfoSearchParam);
                result = licenseInfo != null ? tvPackage + "#" + licenseInfo.getLicense_info() : "";
            }else{
                result = null;
            }
        }

        return result;
    }
}
