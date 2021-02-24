package com.toggle.study.serivce;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.toggle.study.entity.CustQust;
import com.toggle.study.model.common.ResulfDataInfo;
import com.toggle.study.model.request.CustQustInfoRequestDTO;
import com.toggle.study.model.request.CustQustSaveRequestDTO;
import com.toggle.study.repository.CustQustRepository;
import com.toggle.study.util.Utils;


@Service
public class MypageService {
    
    @Autowired
    private CustQustRepository custQustRepository;

    @Autowired
    protected SqlSession sqlsession;

    //문의등록
    public ResulfDataInfo CustQuestionReg(CustQustSaveRequestDTO custQustSaveRequestDTO) {
    	/**
    	 * BeanUtils을 이용해 복사해서 Insert 방법
    	 */
    	CustQust custQust = new CustQust(Utils.getRandomCustQustRegId("CQ"));
    	BeanUtils.copyProperties(custQustSaveRequestDTO, custQust, Utils.getNullPropertyNames(custQustSaveRequestDTO));

        ResulfDataInfo resultInfo=new ResulfDataInfo();

        try {
            custQustRepository.save(custQust);  
            resultInfo.setResultDivCD("OK");
            resultInfo.setResultCD("SUCCESS7103");
            resultInfo.setResultMessage("");
        } catch (Exception e) {
            resultInfo.setResultDivCD("ERR");
            resultInfo.setResultCD("ERROR7103");
            resultInfo.setResultMessage("데이터생성오류");
        }
    	/**
    	 * @Builder 을 이용해 Insert 방법
    	 */
    	//custQustRepository.save(CustQust.builder().insertDTO(custQustSaveRequestDTO).build());        
        return resultInfo;
        
    }

    //고객문의 목록조회
    public Page<CustQust> CustQustList(Pageable pageable) {
        return custQustRepository.findAll(pageable);
    }

    //고객문의 상세조회
    public CustQust CustQustInfo(CustQustInfoRequestDTO custQustInfoRequestDTO) throws Exception{

        CustQust info=sqlsession.selectOne("com.toggle.study.mapper.MypageMapper.custqustinfo",custQustInfoRequestDTO.getQustNo());

        return info;
    }
}