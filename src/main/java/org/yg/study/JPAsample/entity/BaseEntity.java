package org.yg.study.JPAsample.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    @JsonProperty(value = "createdDate", access = JsonProperty.Access.READ_ONLY)
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @JsonProperty(value = "lastModifiedDate", access = JsonProperty.Access.READ_ONLY)
    protected LocalDateTime lastModifiedDate;

    //AuditAware Bean 에서 데이터 를 꺼내온다. 
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
    private String lasteModifiedBy;


    
    
}
