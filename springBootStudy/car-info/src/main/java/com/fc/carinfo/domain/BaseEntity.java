package com.fc.carinfo.domain;

import javax.persistence.*;

import lombok.Data;
import java.util.Date;


@MappedSuperclass
@Data
public abstract class BaseEntity {
  @Temporal(value = TemporalType.TIMESTAMP)
  private Date createdAt;

  @Temporal(value = TemporalType.TIMESTAMP)
  private Date updatedAt;

}