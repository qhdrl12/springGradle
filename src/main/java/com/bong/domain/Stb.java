package com.bong.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ibong-gi on 2016. 12. 5..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.ALWAYS)
@Data
public class Stb implements Serializable{

    @JsonProperty("stbId")
    private String stb_id;

    @JsonProperty("iptvStatusCode")
    private String iptv_status_code;

    @JsonProperty("userServiceNum")
    private String user_service_num;

}
