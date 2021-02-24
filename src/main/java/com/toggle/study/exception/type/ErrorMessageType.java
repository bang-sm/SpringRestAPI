package com.toggle.study.exception.type;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashMap;
import java.util.Map;

public enum ErrorMessageType {

	// @formatter:off
	E400_101("101", "Data Create Failed : %s"),      // 데이터 생성 실패
	E400_102("102", "Not Found"),      // 데이터 조회 실패
	
	
	/** 서버 크리티컬 에러 **/
	E500_101("101", "Interal Error!!!");

    
    // @formatter:on

  private String code;
  private String label;

  private static Map<String, ErrorMessageType> codeToDbStatTypeMapping;

  private ErrorMessageType(String code, String label) {
    this.code = code;
    this.label = label;
  }

  public static ErrorMessageType getType(String i) {
    if (null == codeToDbStatTypeMapping) {
      initMapping();
    }
    ErrorMessageType result = null;
    result = codeToDbStatTypeMapping.get(i);
    return result;
  }

  public static void initMapping() {
    codeToDbStatTypeMapping = new HashMap<String, ErrorMessageType>();
    for (ErrorMessageType error : values()) {
      codeToDbStatTypeMapping.put(error.code, error);
    }
  }

  public String getCode() {
    return code;
  }

  public String getLabel() {
    return label;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
