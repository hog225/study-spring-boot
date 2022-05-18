package org.yg.study.JPAsample.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class JpaBaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdTime;


    @JsonProperty(value = "updateTime", access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updateTime;

    @PrePersist
    public void PrePersist(){
        LocalDateTime now = LocalDateTime.now();
        createdTime = now;
        updateTime = now;
    }

    @PreUpdate
    public void preUpdate(){
        updateTime = LocalDateTime.now();
    }
    
}
