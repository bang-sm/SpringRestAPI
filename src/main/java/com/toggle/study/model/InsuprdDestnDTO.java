package com.toggle.study.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsuprdDestnDTO {

    @JsonProperty(value = "plangrd_code")
    private String plangrd_cd;

    @JsonProperty(value = "sects_number")
    private String scts_no;

    @JsonProperty(value = "fur")
    private List<InsuprdFturDTO> fur;
}
