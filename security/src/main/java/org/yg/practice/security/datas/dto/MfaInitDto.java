package org.yg.practice.security.datas.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MfaInitDto implements Serializable{
    private String username;
    private String secretKey;
    private String type;

    @Builder
    public MfaInitDto(String username, String secretKey, String type){
        this.username = username;
        this.secretKey = secretKey;
        this.type = type;
        
    }
    
}
