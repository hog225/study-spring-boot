package org.yg.practice.security.datas.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.yg.practice.security.datas.dto.MfaDto;
import org.yg.practice.security.datas.dto.MfaInitDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name="mfa", schema = "security")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mfa implements Serializable{

    @Id
    @Column
    private long id;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 512, name = "secret_key")
    private String secretKey;

    @Column(length = 1000)
    private String type; // 인가에 관련되 Autho

    public Mfa(MfaDto mfaDto){
        this.id = mfaDto.getId();
        this.username = mfaDto.getUsername();
        this.secretKey = mfaDto.getSecretKey();
        this.type = mfaDto.getType();
    }

    public Mfa(MfaInitDto mfaInitDto){
        this.username = mfaInitDto.getUsername();
        this.secretKey = mfaInitDto.getSecretKey();
        this.type = mfaInitDto.getType();
    }


    @Builder
    public Mfa(long id, String username, String secretKey, String type){
        this.id = id;
        this.username = username;
        this.secretKey = secretKey;
        this.type = type;
    }

}
