package com.toggle.study.controller.wapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.toggle.study.entity.CustQust;
import com.toggle.study.model.request.CustQustSaveRequestDTO;
import com.toggle.study.serivce.MypageService;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<Void> QuestionReg (@RequestBody CustQustSaveRequestDTO custQustSaveRequestDTO){

        return mypageService.CustQuestionReg(custQustSaveRequestDTO);
    }

    //고객문의 목록조회
    @ResponseBody
    @GetMapping(value="questionlist",produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String,List<CustQust>> QuestionList (){

        HashMap<String,List<CustQust>> map=new HashMap<>();

        map.put("list",mypageService.CustQustList());

        return map;
    }

}
