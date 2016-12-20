package com.bong.svc;

import com.bong.domain.request.StbInfoSearchParam;
import com.bong.domain.response.LicenseInfo;
import com.bong.domain.response.LicenseResponse;
import com.bong.domain.response.Stb;
import com.bong.repository.mappers.StbMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import redis.clients.jedis.JedisCluster;

/**
 * Created by ibong-gi on 2016. 12. 6..
 */
@Slf4j
@Component("stbService")
public class StbServiceImpl implements StbService {
    @Autowired StbMapper stbMapper;
    @Autowired RedisTemplate redisTemplate;
    @Autowired JedisCluster jedisCluster;

    private static boolean redisFlag = false;
    private static int reconCount = 0;

    @Override
    public LicenseResponse getStbStatus(StbInfoSearchParam stbInfoSearchParam) {
        LicenseResponse licenseResponse = new LicenseResponse();
        try{
            String licenseKey;
            String licenseInfo;
            String access;
            String result = "YES";
            String[] licenseInfoArr;

            licenseInfo = jedisCluster.get(stbInfoSearchParam.getStb_id());

            if(licenseInfo != null && (stbInfoSearchParam.getMac_address().equals(licenseInfo.substring(0, licenseInfo.indexOf("#"))))){
                access = "0";
                licenseKey = substrLicenseKey(licenseInfo);
            }else{
                access = "1";
                licenseKey = getLicenseInfoDB(stbInfoSearchParam);
                if(licenseKey == null) {
                    /**
                     *  라이센스에 처리되지 않는 IPTV_STATUS_CODE *
                     *  */
                    result = "FAIL";
                    licenseKey = "ERR|keyisnull";
                }else{
                    licenseInfoArr = licenseKey.split("#");

                    if(licenseInfoArr.length > 1){
                        if(2 < Integer.parseInt(licenseInfoArr[0]) && licenseInfoArr[1].indexOf("err") == -1){
                            jedisCluster.set(stbInfoSearchParam.getStb_id(), stbInfoSearchParam.getMac_address() + "#" + licenseKey);
                            licenseKey = substrLicenseKey(licenseKey);
                        } //TODO Default Channel 추가
                    }else{
                        /**
                         * 1 - 미 가입 (IPTVNOTACCOUNT)
                         * 2 - 청약 중 (Default Channel List)
                         * */
                        if("1".equals(licenseInfoArr[0])){
                            result = "FAIL";
                            licenseKey = "ERR|IPTVNOACCOUNT";
                        }else if("2".equals(licenseInfoArr[0])){
                            licenseKey = "DEFAULT CHANNEL LIST";
                        }
                    }
                }
            }

            reconCount = 0;
            licenseResponse.setAccess(access);
            licenseResponse.setResult(result);
            licenseResponse.setCh_data(licenseKey);
//        }catch(RedisConnectionFailureException e){
//            if(redisFlag){
//                channelRedisRepository = new ChannelRedisRepository(redisSecTemplate);
//                redisFlag = false;
//            }else{
//                channelRedisRepository = new ChannelRedisRepository(redisTemplate);
//                redisFlag = true;
//            }
//            reconCount++;
//
//            if(reconCount == 5){
//                reconCount = 0;
//                return setFailResponse(licenseResponse);
//            }
//
//            log.error("jedis secondary change... reload function");
//            licenseResponse = getStbStatus(stbInfoSearchParam);
        }catch(Exception e){
            log.error("Get Error [ stb_id : " + stbInfoSearchParam.getStb_id() + " ]" + e);
            return setFailResponse(licenseResponse);
        }
        log.info("result : " + licenseResponse.toString());

        return licenseResponse;
    }

    private String getLicenseInfoDB(StbInfoSearchParam stbInfoSearchParam){
        log.info("getLicenseInfoDB Parameter : " + stbInfoSearchParam);
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

    private LicenseResponse setFailResponse(LicenseResponse licenseResponse){
        licenseResponse.setResult("FAIL");
        licenseResponse.setAccess("0");
        licenseResponse.setCh_data("");

        return licenseResponse;
    }

    private String substrLicenseKey(String licenseKey){
        return licenseKey.substring(licenseKey.lastIndexOf("#")+1);
    }
}
