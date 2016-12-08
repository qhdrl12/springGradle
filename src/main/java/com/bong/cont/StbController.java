package com.bong.cont;

import com.bong.com.FixturesProperty;

import com.bong.domain.request.StbInfoSearchParam;
import com.bong.domain.response.LicenseResponse;
import com.bong.domain.response.LicenseResponseList;
import com.bong.svc.StbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody public LicenseResponseList searchLicenseKey(@ModelAttribute StbInfoSearchParam stbInfoSearchParam) {
        log.info("model attribute : " + stbInfoSearchParam);
        LicenseResponseList licenseResponseList = new LicenseResponseList();
        try {
            licenseResponseList.setLicenseResponseList(new LicenseResponse(stbService.getStbStatus(stbInfoSearchParam), "1", "YES"));
        }catch (Exception e){
            licenseResponseList.setLicenseResponseList(new LicenseResponse("err", "1", "EXCEPTION"));
        }
        log.info(licenseResponseList.toString());
        return licenseResponseList;
    }
}
