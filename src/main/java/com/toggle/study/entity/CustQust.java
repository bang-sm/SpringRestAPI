package com.toggle.study.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 고객문의 Entity
 * 
 */
@Entity
@NoArgsConstructor
@Getter
@ToString
@Table(name = "cust_qust")
public class CustQust extends BaseAllSerialzable{

	private static final long serialVersionUID = 1L;

    //등록ID
    @Id
    @Column(name = "cust_qust_reg_id", nullable = false)
    private String custQustRegId;

    //문의번호
    @Column(name = "qust_no", nullable = false)
    private String qustNo;

    //고객명(이메일
    @Column(name = "cust_nm", nullable = false)
    private String custNm;

    //문의구분코드
    @Column(name = "qust_dv_cd", nullable = false)
    private String qustDvCd;

    //답변일
    @Column(name = "answ_day", nullable = false)
    private String answDay;
    
    //제목
    @Column(name = "title", nullable = false)
    private String title;

    //내용
    @Column(name = "ctt", nullable = false)
    private String ctt;

    //답변내용
    @Column(name = "answ", nullable = false)
    private String answ;

    @Builder
    public CustQust(String title, String custNm, String ctt, String qustDvCd) {
        this.title = title;
        this.custNm = custNm;
        this.ctt = ctt;
        this.qustDvCd = qustDvCd;
    }

    //등록ID생성 PK
    public void setCustQustRegId(String custQustRegId){
        //////등록ID만드는 함수로 변경예정//////
        Random r = new Random();
        StringBuilder sb = new StringBuilder(4);

        for(int i = 0; i < 4; i++) {
            char tmp = (char) ('a' + r.nextInt('z' - 'a'));
            sb.append(tmp);
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date srvrTime = new Date();

        String time = format.format(srvrTime);
        //고객문의등록ID생성
        String id=time+sb.toString();

        this.custQustRegId=id;
    }
}
