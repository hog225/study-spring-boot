package org.yg.memo.security.handler;


import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.yg.memo.security.dto.ClubAuthMemberDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
// Social 로그인 성공시 Password를 변경하는 화면으로 Redirect 시키는 코드
public class ClubLoginSuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private PasswordEncoder passwordEncoder;

    public ClubLoginSuccessHandler(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("-----onAuthenticationSuccess INFO START-----");
        ClubAuthMemberDTO authmember = (ClubAuthMemberDTO)authentication.getPrincipal();

        boolean fromSocial = authmember.isFromSocial();
        log.info("needy modify member ? " + fromSocial);

        boolean passwordResult = passwordEncoder.matches("1111", authmember.getPassword());

        if (fromSocial && passwordResult){
            //http://localhost:8080/login/oauth2/code/member/modify?from=social 여기로 리다이렉트 HTML 만들고 변경 코드 입력 하면 됨
            redirectStrategy.sendRedirect(request, response, "member/modify?from=social");
            //http://localhost:8080/sample/member/modify?from=social# 리다이렉트
            //redirectStrategy.sendRedirect(request, response, "/sample/member/modify?from=social");

        }

    }
}
