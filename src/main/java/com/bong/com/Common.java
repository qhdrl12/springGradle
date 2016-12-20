package com.bong.com;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;

/**
 * Created by ibong-gi on 2016. 12. 20..
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "spring.common")
public class Common{

    private List<String> ips = null;
    private String defaultLicenseKey;
    private Map<String, String> sync = new WeakHashMap<>();

    @PostConstruct
    private void init(){
        ips = new ArrayList<>();
        try{
            /**
            *  서버 IP 주소 ips 저장
            * */
            for(Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();){
                NetworkInterface ni = en.nextElement();
                for(Enumeration<InetAddress> enumIpAddr = ni.getInetAddresses(); enumIpAddr.hasMoreElements();){
                    InetAddress inetAddress = enumIpAddr.nextElement();

                    if(!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()){
                        ips.add("http://" + inetAddress.getHostAddress().toString() + ":8080");
                    }
                }
            }

            log.info("sync Map : " + sync);

        }catch (Exception e){
            log.error("init Exception : " + e);
        }
    }
}
