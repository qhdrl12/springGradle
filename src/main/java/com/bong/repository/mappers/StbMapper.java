package com.bong.repository.mappers;

import com.bong.domain.response.LicenseInfo;
import com.bong.domain.response.Stb;
import com.bong.domain.request.StbInfoSearchParam;
import org.springframework.stereotype.Repository;

/**
 * Created by ibong-gi on 2016. 12. 5..
 */

@Repository
public interface StbMapper {
    Stb getStbInfo(StbInfoSearchParam stbInfoSearchParam);
    LicenseInfo getLicenseInfo(StbInfoSearchParam stbInfoSearchParam);

}
