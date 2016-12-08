package com.bong.svc;

import com.bong.domain.request.StbInfoSearchParam;

/**
 * Created by ibong-gi on 2016. 12. 6..
 */
public interface StbService {
    String getStbStatus(StbInfoSearchParam stbInfoSearchParam);
}
