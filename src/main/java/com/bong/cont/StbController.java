package com.bong.cont;

import com.bong.domain.request.StbInfoSearchParam;
import com.bong.domain.response.LicenseResponse;
import com.bong.domain.response.LicenseResponseList;
import com.bong.svc.StbService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by qhdrl on 2016-12-01.
 */

@Slf4j
@Controller
public class StbController {

//    @Autowired private FixturesProperty fixturesProperty;
    @Autowired StbService stbService;

    @ RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(){
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody public LicenseResponseList searchLicenseKey(@ModelAttribute StbInfoSearchParam stbInfoSearchParam) {
        log.info("model attribute : " + stbInfoSearchParam);
        LicenseResponseList licenseResponseList = new LicenseResponseList();
        try {
            licenseResponseList.setLicenseResponseList(stbService.getStbStatus(stbInfoSearchParam));
        }catch (Exception e){
            licenseResponseList.setLicenseResponseList(new LicenseResponse("err", "1", "EXCEPTION"));
        }
        return licenseResponseList;
    }
}
