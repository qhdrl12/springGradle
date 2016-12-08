package com.bong.cont;

import com.bong.com.FixturesProperty;

import com.bong.domain.LicenseResponse;
import com.bong.domain.LicenseResponseList;
import com.bong.svc.StbService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by qhdrl on 2016-12-01.
 */

@Slf4j
@Controller
public class StbController {

    @Autowired private FixturesProperty fixturesProperty;
    @Autowired StbService stbService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody public LicenseResponseList indexPage(HttpServletRequest request) {
        log.info("request : " + request.getQueryString());
        LicenseResponseList licenseResponseList = new LicenseResponseList();
        try {
            licenseResponseList.setLicenseResponseList(new LicenseResponse(stbService.getStbStatus(), "1", "YES"));
        }catch (Exception e){
            licenseResponseList.setLicenseResponseList(new LicenseResponse("err", "1", "EXCEPTION"));
        }
        log.info(licenseResponseList.toString());
        return licenseResponseList;
    }
}
