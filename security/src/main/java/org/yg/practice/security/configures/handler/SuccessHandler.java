package org.yg.practice.security.configures.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.yg.practice.security.datas.dto.CustomUserDetails;

import java.io.IOException;

@Slf4j
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String successUrl = "/main";
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("password");
        request.getSession().removeAttribute("mfa");
        this.setDefaultTargetUrl(successUrl);
        HttpSession httpSession = request.getSession();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        customUserDetails.setPassword(null);
        httpSession.setAttribute("userInfo", customUserDetails);

        authentication.getAuthorities().stream().forEach(grantedAuthority -> {
                log.info("grantedAuthority" + grantedAuthority.getAuthority());
        });

        super.onAuthenticationSuccess(request, response, authentication);
    }
}

