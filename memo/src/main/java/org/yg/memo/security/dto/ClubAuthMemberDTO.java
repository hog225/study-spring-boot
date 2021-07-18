package org.yg.memo.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

// 일반적이 Form 로그인 방식에서는 로그인 시 ClubUserDetailService 에서 ClubAuthMemberDTO를 리턴하여
// Controller 에서 ClubAuthMemberDTO를 받아서 처리 가능하다. (AuthenticationPrincipal)
//
@Log4j2
@Getter
@Setter
public class ClubAuthMemberDTO extends User implements OAuth2User {

    private String email;
    private String password;
    private String name;
    private boolean fromSocial;
    private Map<String, Object> attr;

    public ClubAuthMemberDTO(
            String username,
            String password,
            boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities){
        super(username, password, authorities);
        this.email = username;
        this.password = password;
        this.fromSocial = fromSocial;
    }

    public ClubAuthMemberDTO(
            String username,
            String password,
            boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities,
            Map<String, Object> attr){
        this(username, password, fromSocial, authorities);
        this.attr = attr;
        this.email = username;
        this.password = password;
        this.fromSocial = fromSocial;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return this.attr;
    }
}
