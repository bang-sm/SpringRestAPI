package com.toggle.study.serivce;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.toggle.study.entity.CustQust;
import com.toggle.study.model.request.CustQustSaveRequestDTO;
import com.toggle.study.repository.CustQustRepository;
import com.toggle.study.util.Utils;


@Service
public class MypageService {
    
    @Autowired
    private CustQustRepository custQustRepository;

    //문의등록
    public ResponseEntity<Void> CustQuestionReg(CustQustSaveRequestDTO custQustSaveRequestDTO) {
    	/**
    	 * BeanUtils을 이용해 복사해서 Insert 방법
    	 */
    	CustQust custQust = new CustQust(Utils.getRandomCustQustRegId("CQ"));
    	BeanUtils.copyProperties(custQustSaveRequestDTO, custQust, Utils.getNullPropertyNames(custQustSaveRequestDTO));
    	custQustRepository.save(custQust);  
    	
    	/**
    	 * @Builder 을 이용해 Insert 방법
    	 */
    	//custQustRepository.save(CustQust.builder().insertDTO(custQustSaveRequestDTO).build());        
        return new ResponseEntity<Void>(HttpStatus.CREATED);
        
    }

    //고객문의 목록조회
    public Page<CustQust> CustQustList(Pageable pageable) {
    	

        return custQustRepository.findAll(pageable);
    }
    
}
