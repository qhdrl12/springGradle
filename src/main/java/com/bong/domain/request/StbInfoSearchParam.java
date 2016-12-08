package com.bong.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by ibong-gi on 2016. 12. 8..
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.ALWAYS)
@Data
public class StbInfoSearchParam {

    @JsonProperty("mac_address")
    private String mac_address;

    @JsonProperty("stb_id")
    private String stb_id;

    @Override
    public String toString(){
        return "StbInfoSearchParam{" +
                "stb_id='" + stb_id + '\'' +
                "mac_address='" + mac_address +
               '}';
    }
}
