package org.yg.study.JPAsample.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class JpaBaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdTime;
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
