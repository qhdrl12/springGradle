package com.bong.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibong-gi on 2016. 12. 8..
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.ALWAYS)
@XmlRootElement(name = "root")
@Data
public class LicenseResponseList{

    @JsonProperty("row")
    List<LicenseResponse> row;

    public LicenseResponseList() {
        row = new ArrayList<>();
    }

    public LicenseResponseList(List<LicenseResponse> row){
        this.row = row;
    }

    public void setLicenseResponseList(LicenseResponse licenseResponse){
        row.clear();
        row.add(licenseResponse);
    }

    @Override
    public String toString(){
        return "LicenseResponseList{" +
                "row='" + row + '\'' +
                '}';
    }
}
