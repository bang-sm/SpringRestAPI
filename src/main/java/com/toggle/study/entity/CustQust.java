package com.toggle.study.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.toggle.study.model.request.CustQustSaveRequestDTO;
import com.toggle.study.util.Utils;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 고객문의 Entity
 * 
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "cust_qust")
public class CustQust extends BaseAllSerialzable{

	private static final long serialVersionUID = 1L;

    //등록ID
    @Id
    @Column(name = "cust_qust_reg_id", nullable = false)
    private String custQustRegId;

    //문의번호
    @Column(name = "qust_no")
    private String qustNo;

    //고객명(이메일
    @Column(name = "cust_nm")
    private String custNm;

    //문의구분코드
    @Column(name = "qust_dv_cd")
    private String qustDvCd;

    //답변일
    @Column(name = "answ_day")
    private String answDay;
    
    //제목
    @Column(name = "title")
    private String title;

    //내용
    @Column(name = "ctt")
    private String ctt;

    //답변내용
    @Column(name = "answ")
    private String answ;
    
    
    public CustQust(String custQustRegId) {
    	this.custQustRegId = custQustRegId;
    }

    @Builder
    public CustQust(CustQustSaveRequestDTO insertDTO) {
    	this.custQustRegId = Utils.getRandomCustQustRegId();
    	this.custNm = insertDTO.getCustNm();
    	this.title = insertDTO.getTitle();
    	this.ctt = insertDTO.getCtt();
		this.qustDvCd = insertDTO.getQustDvCd();
    }
    
}
