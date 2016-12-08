package com.bong.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ibong-gi on 2016. 12. 7..
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.ALWAYS)
@Data
public class LicenseResponse implements Serializable {

    @JsonProperty("ch_data") private String ch_data;
    @JsonProperty("access") private String access;
    @JsonProperty("result") private String result;

    public LicenseResponse(){}

    public LicenseResponse(String ch_data, String access, String result){
        this.ch_data = ch_data;
        this.access = access;
        this.result = result;
    }

    @Override
    public String toString(){
        return "LicenseResponse{" +
                "result='" + result + '\'' +
                "access='" + access + '\'' +
                "ch_data='" + ch_data +
               '}';
    }
}
