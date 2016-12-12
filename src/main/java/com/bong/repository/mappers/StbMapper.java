package com.bong.repository.mappers;

import com.bong.domain.response.LicenseInfo;
import com.bong.domain.response.Stb;
import com.bong.domain.request.StbInfoSearchParam;

/**
 * Created by ibong-gi on 2016. 12. 5..
 */
public interface StbMapper {
    Stb getStbInfo(StbInfoSearchParam stbInfoSearchParam);
    LicenseInfo getLicenseInfo(StbInfoSearchParam stbInfoSearchParam);
}
