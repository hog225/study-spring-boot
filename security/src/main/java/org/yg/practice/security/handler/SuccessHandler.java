package org.yg.practice.security.handler;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.yg.practice.security.datas.dto.CustomUserDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

    @Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		String successUrl = "/main";

        this.setDefaultTargetUrl(successUrl);

        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("password");
        request.getSession().removeAttribute("mfa");

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        customUserDetails.setPassword(null);

        request.getSession().setAttribute("userInfo", customUserDetails);
        super.onAuthenticationSuccess(request, response, authentication);
		
	}
}
