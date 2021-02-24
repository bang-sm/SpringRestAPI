package com.toggle.study.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustQustInfoRequestDTO {

    @JsonProperty(value = "qust_no")
    private String qustNo;
}
