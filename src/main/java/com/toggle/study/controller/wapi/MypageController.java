package com.toggle.study.controller.wapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.toggle.study.entity.CustQust;
import com.toggle.study.model.common.ResulfDataInfo;
import com.toggle.study.model.reponse.PageResponse;
import com.toggle.study.model.request.CustQustSaveRequestDTO;
import com.toggle.study.serivce.MypageService;


@RestController
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    private MypageService mypageService;
    
    //문의등록
    //
    @ResponseBody
    @PostMapping(value="questionreg",produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResulfDataInfo> QuestionReg (@RequestBody CustQustSaveRequestDTO custQustSaveRequestDTO){

        ResulfDataInfo resultInfo = mypageService.CustQuestionReg(custQustSaveRequestDTO);

       return new ResponseEntity<ResulfDataInfo>(resultInfo,HttpStatus.CREATED);
    }

    //고객문의 목록조회
    @SuppressWarnings({"unchecked", "rawtypes"})
	@ResponseBody
    @GetMapping(value="questionlist")
    public ResponseEntity<PageResponse> QuestionList (Pageable page){
        Page<CustQust> list = mypageService.CustQustList(page);
        return new ResponseEntity<PageResponse>(new PageResponse(list.getTotalElements(), list.getTotalPages(), list.getNumber(), list.getContent()), HttpStatus.OK);
    }

}
