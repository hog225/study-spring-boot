package org.yg.practice.security.configures.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.extern.slf4j.Slf4j;

// 필터 사용자 정보를 처리 
@Slf4j
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomUsernamePasswordAuthenticationFilter() {
        super();
    }

    public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }


    private boolean postOnly = true;
    private SessionAuthenticationStrategy sessionAuthenticationStrategy = new NullAuthenticatedSessionStrategy();
    private boolean continueChainBeforeSuccessfulAuthentication = false;
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login",
    "POST");


    @Override
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

    @Override
    public void setContinueChainBeforeSuccessfulAuthentication(boolean continueChainBeforeSuccessfulAuthentication) {
		this.continueChainBeforeSuccessfulAuthentication = continueChainBeforeSuccessfulAuthentication;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (this.postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		String username = this.obtainUsername(request);
		username = (username != null) ? username : "";
		username = username.trim();
		String password = this.obtainPassword(request);
		password = (password != null) ? password : "";
        String otp = this.obtainOtp(request);
        otp = (otp != null) ? otp:"";
        otp = otp.trim();

        Object object = request.getSession().getAttribute("mfa");
        boolean mfa = (object == null) ? false:(boolean)object;
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        if (mfa){
            authRequest.setDetails(otp);
        }
		
        //provider로 넘긴다.
        return super.getAuthenticationManager().authenticate(authRequest);
	}

    private String obtainOtp(HttpServletRequest request) {
        return (String) request.getParameter("otp");
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
		return (String) request.getSession().getAttribute("username");
	}

    @Override
    protected String obtainPassword(HttpServletRequest request) {
		return (String) request.getSession().getAttribute("password");
	}

    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
        
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        log.info("doFilter" + ' ' + httpServletRequest.getServletPath() + ' ' + ' '+ httpServletRequest.getMethod());
        
        if (!requiresAuthentication(httpServletRequest, httpServletResponse)){
            log.info("Auth no need");
            chain.doFilter(request, response);
            return;
        }

        try {
            Authentication authentication = attemptAuthentication(httpServletRequest, httpServletResponse);
            if (authentication == null)
                return;

            this.sessionAuthenticationStrategy.onAuthentication(authentication, httpServletRequest, httpServletResponse);
            if (this.continueChainBeforeSuccessfulAuthentication){
                chain.doFilter(request, response);
            }
            successfulAuthentication(httpServletRequest, httpServletResponse, chain, authentication);
        } catch (InternalAuthenticationServiceException e){
            unsuccessfulAuthentication(httpServletRequest, httpServletResponse, e);
        } catch (AuthenticationException e){
            unsuccessfulAuthentication(httpServletRequest, httpServletResponse, e);
        }
		
	}


}
