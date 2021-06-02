package org.yg.practice.security.datas.dto;

import java.io.Serializable;
import java.util.Optional;

import org.yg.practice.security.datas.entities.Mfa;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MfaProveDto implements Serializable{
    
    private String secretKey;
    private String type;

    public MfaProveDto(Mfa mfa){
        if (Optional.ofNullable(mfa).isPresent()){
            this.secretKey = mfa.getSecretKey();
            this.type = mfa.getType();    
        }
    }

    @Builder
    public MfaProveDto(String secretKey, String type){
        this.secretKey = secretKey;
        this.type = type;
        
    }
    
}
