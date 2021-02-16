package com.toggle.sutdy.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 * 보험상품 Entity
 * 
 */
@Entity
@Table(name = "insuprd")
public class Insuprd extends BaseAllSerialzable{

  private static final long serialVersionUID = 1L;

  /**
   * 보험상품 등록 ID
   */
  @Id
  @Size(max = 20)
  @Column(name = "insuprd_reg_id", nullable = false)
  private String insuprdRegId;

  /**
   * 보험상품 명
   */
  @Size(max = 50)
  @Column(name = "insuprd_nm")
  private String insuprdNm;

  /**
   * 노출 보험상품 명
   */
  @Size(max = 20)
  @Column(name = "xps_insuprd_nm")
  private String xpsInsuprdNm;

  /**
   * 보험 종류 코드
   */
  @Size(max = 20)
  @Column(name = "insu_kind_cd")
  private String insuKindCd;
  
  /**
   * 보험상품 분류 코드
   */
  @Size(max = 20)
  @Column(name = "insuprd_clas_cd")
  private String insuprdClasCd;
  
  /**
   * 보험 요율 구분 코드
   */
  @Size(max = 20)
  @Column(name = "insu_rate_dv_cd")
  private String insuRateDvCd;
  
  /**
   * 보험상품 가입 UI 코드
   */
  @Size(max = 20)
  @Column(name = "insuprd_ntry_ui_cd")
  private String insuprdNtryUiCd;
  
  /**
   * 보험상품 상태 코드
   */
  @Size(max = 20)
  @Column(name = "insuprd_st_cd")
  private String insuprdStCd;
  
  /**
   * 보험상품 발급 번호
   */
  @Size(max = 20)
  @Column(name = "insuprd_issu_no")
  private String insuprdIssuNo;
  
  /**
   * 판매 시작 일시
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "sale_str_dtm")
  private Date saleStrDtm;
  
  /**
   * 판매 종료 일시
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "sale_end_dtm")
  private Date saleEndDtm;
  
  /**
   * 가입 증명서 제공 코드
   */
  @Size(max = 20)
  @Column(name = "ntry_crtf_offr_cd")
  private String ntryCrtfOffrCd;
  
}
