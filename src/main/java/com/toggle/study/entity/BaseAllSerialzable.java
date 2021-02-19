package com.toggle.study.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Getter;
import lombok.Setter;

/**
 * 다수의 테이블에 적용 되는 공통 Entity
 */
@MappedSuperclass
@Getter
@Setter
public class BaseAllSerialzable implements Serializable {

  private static final long serialVersionUID = 1L;
  
  /**
   * 등록일
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "regday", updatable = false)
  @CreationTimestamp
  private Date regDay;
  
  /**
   * 등록자
   */
  @Size(max = 20)
  @Column(name = "regr")
  private String regr;

  /**
   * 수정일
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "modd")
  private Date modd;
  
  /**
   * 수정자
   */
  @Size(max = 20)
  @Column(name = "modifr")
  private String modIfr;

  @PrePersist
  public void prePersist() {
    setRegDay(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
  }

  @PreUpdate
  public void preUpdate() {
    setModd(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
  }
}
