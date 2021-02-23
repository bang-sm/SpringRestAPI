package com.toggle.study.model.reponse;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResponse<T> {

  private Integer totalRecords;
  private Integer totalPages;
  private Integer pageNumber;

  private List<T> list;

  public PageResponse() {}

  public PageResponse(long totalRecords, Integer totalPages, Integer pageNumber, List<T> list) {
    this.totalRecords = (int) totalRecords;
    this.totalPages = totalPages;
    this.pageNumber = pageNumber;
    this.list = list;
  }


}
