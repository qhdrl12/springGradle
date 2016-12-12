package com.bong.svc;

import com.bong.domain.request.StbInfoSearchParam;
import com.bong.domain.response.LicenseResponse;

/**
 * Created by ibong-gi on 2016. 12. 6..
 */
public interface StbService {
    LicenseResponse getStbStatus(StbInfoSearchParam stbInfoSearchParam);
}
