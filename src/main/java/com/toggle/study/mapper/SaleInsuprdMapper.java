package com.toggle.study.mapper;

import java.util.List;

import com.toggle.study.model.SaleInsuprdDTO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SaleInsuprdMapper {

    //판매상품조회
    public List<SaleInsuprdDTO> saleInsuprd() throws Exception;
}
