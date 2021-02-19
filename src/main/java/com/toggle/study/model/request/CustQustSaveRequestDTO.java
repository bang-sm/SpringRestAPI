package com.toggle.study.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CustQustSaveRequestDTO {
    @JsonProperty(value = "cust_nm")
    private String custNm;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "ctt")
    private String ctt;

    @JsonProperty(value = "qust_dv_cd")
    private String qustDvCd;

}
