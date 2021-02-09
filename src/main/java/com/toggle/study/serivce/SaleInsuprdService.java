package com.toggle.study.serivce;

import java.util.List;

import com.toggle.study.model.SaleInsuprdDTO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleInsuprdService {

    // @Autowired
    // private SaleInsuprdMapper saleInsuprdMapper;

    @Autowired
    protected SqlSession sqlsession;
    
    public List<SaleInsuprdDTO> saleInsuprd() throws Exception {

        List<SaleInsuprdDTO> sale=sqlsession.selectList("com.toggle.study.mapper.SaleInsuprd.saleInsuprd");

		return sale;
	}
    
}
