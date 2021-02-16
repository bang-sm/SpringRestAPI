package com.toggle.sutdy.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
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
public class BaseModSerialzable implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 수정일
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "modd")
  @CreationTimestamp
  private Date modd;
  
  /**
   * 수정자
   */
  @Size(max = 20)
  @Column(name = "modifr")
  private String modIfr;


  @PreUpdate
  public void preUpdate() {
    setModd(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
  }
}
