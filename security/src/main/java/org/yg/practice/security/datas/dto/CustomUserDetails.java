package org.yg.practice.security.datas.dto;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// UsernamePasswordAuthentication 필터는 UserDetails 를 이용해 인증을 처리 
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
//사용자 정보를 담는 인터페이스 
public class CustomUserDetails implements UserDetails{
    private String username;
    private String password;
    private List<? extends GrantedAuthority> authorities;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

}
