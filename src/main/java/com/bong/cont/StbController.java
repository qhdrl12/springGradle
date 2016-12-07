package com.bong.cont;

import com.bong.com.FixturesProperty;

import com.bong.domain.LicenseResponse;
import com.bong.svc.StbService;
import lombok.extern.slf4j.Slf4j;
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

//        List<Article> article = fixturesProperty.getArticles();
//        log.info("article : " + article.get(0).getTitle());

//@RequestMapping(value = "/", method = RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE)
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = { "application/xml", "text/xml" }, consumes = MediaType.ALL_VALUE)
    public  @ResponseBody LicenseResponse indexPage() {


        LicenseResponse licenseResponse = new LicenseResponse();
        licenseResponse.setCh_data(stbService.getStbStatus());
        licenseResponse.setAccess("1");
        licenseResponse.setResult("YES");

        log.info(licenseResponse.toString());
        return licenseResponse;
    }
}
