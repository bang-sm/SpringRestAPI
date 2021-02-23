package com.toggle.study.model.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest {

  private Integer pageSize;

  private Integer pageNumber;

  private String sort;

  private String order;

  public PageRequest() {

  }

  public PageRequest(Integer pageNumber, Integer pageSize, String order, String sort) {
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.order = order;
    this.sort = sort;
  }

  public PageRequest(PageRequest request) {
    this.pageSize = request.getPageSize();
    this.pageNumber = request.getPageNumber();
    this.sort = request.getSort();
    this.order = request.getOrder();
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
