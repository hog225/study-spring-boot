package org.yg.practice.security.datas.dto;

import java.io.Serializable;
import java.util.Optional;


import org.yg.practice.security.datas.entities.Mfa;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MfaDto implements Serializable {


    private long id;
    private String username;
    private String password;
    private String secretKey;
    private String type;
    private String otpNumber;

    public MfaDto(Mfa mfa){
        if(Optional.ofNullable(mfa).isPresent()){
            this.id = mfa.getId();
            this.username = mfa.getUsername();
            this.secretKey = mfa.getSecretKey();
            this.type = mfa.getType();
        }
        
    }
    

    @Builder
    public MfaDto(long id, String username, String password, String secretKey, String type, String otpNumber){
        this.id = id;
        this.username = username;
        this.password = password;
        this.secretKey = secretKey;
        this.type = type;
        this.otpNumber = otpNumber;
    }

}
