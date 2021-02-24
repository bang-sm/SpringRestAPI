package com.toggle.study.mapper;

import com.toggle.study.entity.CustQust;
import com.toggle.study.model.request.CustQustInfoRequestDTO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MyPageMapper {
      //고객문의상세조회
      public CustQust CustQustInfo(CustQustInfoRequestDTO custQustInfoRequestDTO) throws Exception;

}
