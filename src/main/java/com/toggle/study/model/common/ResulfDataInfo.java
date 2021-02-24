package com.toggle.study.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResulfDataInfo {
    @JsonProperty(value="result_div_cd")
    String ResultDivCD;
    @JsonProperty(value="result_cd")
    String ResultCD;
    @JsonProperty(value="result_msg")
    String ResultMessage;
}
