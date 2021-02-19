package com.toggle.study.serivce;

import com.toggle.study.entity.CustQust;
import com.toggle.study.model.request.CustQustSaveRequestDTO;
import com.toggle.study.repository.CustQustRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class MypageService {
    
    @Autowired
    private CustQustRepository custQustRepository;

    //문의등록
    public ResponseEntity<Void> CustQuestionReg(CustQustSaveRequestDTO custQustSaveRequestDTO) {

    	custQustRepository.save(CustQust.builder().insertDTO(custQustSaveRequestDTO).build());        
        return new ResponseEntity<Void>(HttpStatus.CREATED);
        
    }
    
}
