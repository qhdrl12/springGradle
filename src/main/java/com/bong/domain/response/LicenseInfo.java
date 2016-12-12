package com.bong.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ibong-gi on 2016. 12. 12..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.ALWAYS)
@Data
public class LicenseInfo implements Serializable{
    @JsonProperty("license_info")
    private String license_info;

    @Override
    public String toString(){
        return "LicenseInfo{" +
                "license_info='" + license_info +
                '}';
    }
}
