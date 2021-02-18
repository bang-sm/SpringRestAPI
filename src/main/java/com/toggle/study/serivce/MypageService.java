package com.toggle.study.serivce;

import com.toggle.study.model.request.CustQustSaveRequestDTO;
import com.toggle.study.repository.CustQustRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MypageService {
    
    @Autowired
    private CustQustRepository custQustRepository;

    //문의등록
    public String CustQuestionReg(CustQustSaveRequestDTO custQustSaveRequestDTO) {

        String state="";
        
        try {
            System.out.println("트라이시도");
            System.out.println(custQustSaveRequestDTO.toEntity().toString());
            custQustRepository.save(custQustSaveRequestDTO.toEntity());

            state="ok";

        } catch (Exception e) {
            System.out.println("예외발생");
            e.toString();
            state="err";
        } 

        return state;
    }
}
