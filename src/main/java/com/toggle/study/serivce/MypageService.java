package com.toggle.study.serivce;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.toggle.study.entity.CustQust;
import com.toggle.study.exception.E400_101DataCreateFailedException;
import com.toggle.study.exception.E400_102NotFoundException;
import com.toggle.study.model.common.ResulfDataInfo;
import com.toggle.study.model.request.CustQustSaveRequestDTO;
import com.toggle.study.repository.CustQustRepository;
import com.toggle.study.util.Utils;


@Service
public class MypageService {
    
    @Autowired
    private CustQustRepository custQustRepository;

    //문의등록
    public ResulfDataInfo CustQuestionReg(CustQustSaveRequestDTO custQustSaveRequestDTO) {
    	/**
    	 * BeanUtils을 이용해 복사해서 Insert 방법
    	 */
    	CustQust custQust = new CustQust(Utils.getRandomCustQustRegId("CQ"));
    	BeanUtils.copyProperties(custQustSaveRequestDTO, custQust, Utils.getNullPropertyNames(custQustSaveRequestDTO));

        try {
            custQustRepository.save(custQust);  
            return new ResulfDataInfo("OK", "SUCCESS7103", "");
        } catch (Exception e) {
        	throw new E400_101DataCreateFailedException("CustQuestionReg");
        	//throw new E400_102NotFoundException();
        }
    	/**
    	 * @Builder 을 이용해 Insert 방법
    	 */
    	//custQustRepository.save(CustQust.builder().insertDTO(custQustSaveRequestDTO).build());        
        
    }

    //고객문의 목록조회
    public Page<CustQust> CustQustList(Pageable pageable) {
        return custQustRepository.findAll(pageable);
    }
    
}
