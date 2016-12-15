package com.bong.domain.response;

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

    @JsonProperty private String iptv_status_code;
    @JsonProperty private String user_service_num;
    @JsonProperty private String user_init_num;
    @JsonProperty private String id_package;
    @JsonProperty private String tv_package;
    @JsonProperty private String network_type;
    @JsonProperty private String iptv_usable;
    @JsonProperty private String post_no;
    @JsonProperty private String model_code;
    @JsonProperty private String service_code;

    @Override
    public String toString(){
        return "Stb{" +
                "iptv_status_code='" + iptv_status_code + '\'' +
                "user_service_num='" + user_service_num + '\'' +
                "user_init_num='" + user_init_num + '\'' +
                "id_package='" + id_package + '\'' +
                "tv_package='" + tv_package + '\'' +
                "netwmodel_codeork_type='" + network_type + '\'' +
                "iptv_usable='" + iptv_usable + '\'' +
                "post_no='" + post_no + '\'' +
                "model_code='" + model_code + '\'' +
                "user_init_num='" + user_init_num +
               '}';
    }
}
