package com.toggle.study.serivce;

import java.util.List;

import com.toggle.study.VO.Criteria;
import com.toggle.study.model.SaleInsuprdDTO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleInsuprdService {

    @Autowired
    protected SqlSession sqlsession;
    
    public List<SaleInsuprdDTO> saleInsuprd(Criteria cri) throws Exception {

        List<SaleInsuprdDTO> sale=sqlsession.selectList("com.toggle.study.mapper.SaleInsuprdMapper.pagesaleinsuprd",cri);

		return sale;
	}
    
}
