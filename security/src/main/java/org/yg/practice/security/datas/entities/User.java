package org.yg.practice.security.datas.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Entity
@ToString
@EqualsAndHashCode(callSuper = false)
@Table(name="users", schema = "security")
public class User {

    @Id
    @Column(nullable = false)
    private long id;

    @Column(length = 50)
    private String username;

    @Column(length = 512)
    private String password;

    @Column(length = 1000)
    private String roles; // 인가에 관련되 Autho

    @Builder
    public User(long id, String username, String password, String roles){
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

}
