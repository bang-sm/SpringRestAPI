package com.toggle.study.mapper;

import java.util.List;
import java.util.Map;

import com.toggle.study.model.InsuprdDTO;
import com.toggle.study.model.InsuprdDestnDTO;
import com.toggle.study.model.InsuprdFturDTO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface InsuprdMapper {
    
    //보험상품정보
    public List<InsuprdDTO> selectInsuprd() throws Exception;

    //상품설명조회
    public InsuprdDestnDTO saleInsuprd(Map<String, Object> param) throws Exception;
     //상품특징조회
    public List<InsuprdFturDTO> insuprdftur(Map<String, Object> param) throws Exception;

}
