package com.toggle.study.controller.wapi;

import com.toggle.study.model.request.CustQustSaveRequestDTO;
import com.toggle.study.serivce.MypageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    private MypageService mypageService;
    
    //문의등록
    @ResponseBody
    @PostMapping(value="questionreg",produces = { MediaType.APPLICATION_JSON_VALUE })
    public String QuestionReg (@RequestBody CustQustSaveRequestDTO custQustSaveRequestDTO){

        String state=mypageService.CustQuestionReg(custQustSaveRequestDTO);

        return state;
    }

}
