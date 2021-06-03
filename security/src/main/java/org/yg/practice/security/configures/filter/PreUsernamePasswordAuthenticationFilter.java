package org.yg.practice.security.configures.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.yg.practice.security.datas.dto.MfaDto;
import org.yg.practice.security.datas.entities.UserEntity;
import org.yg.practice.security.services.MfaService;
import org.yg.practice.security.services.UserService;

import lombok.extern.slf4j.Slf4j;

//실제 인증을 타기 전에 해당 사용자가 맞는지 아닌지 사용자가 DTO 가 설정 되어 있는지 아닌지 판단하여 어느 화면으로 이동하여 작업할지를 결정하는 
@Slf4j
public class PreUsernamePasswordAuthenticationFilter implements Filter{
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final MfaService mfaService;

    public PreUsernamePasswordAuthenticationFilter(PasswordEncoder passwordEncoder, UserService userService, MfaService mfaService){
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.mfaService = mfaService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
    FilterChain chain) throws IOException, ServletException{
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        log.info("doFilter" + ' ' + httpServletRequest.getServletPath() + ' ' + httpServletRequest.getPathInfo()+ ' '+ httpServletRequest.getMethod());
        // prelogin post 에 대해 처리 하겠다. 
        if (httpServletRequest.getServletPath().equals("/prelogin") && httpServletRequest.getMethod().equals("POST")){
            String username = httpServletRequest.getParameter("username");
            String password = httpServletRequest.getParameter("password");
            UserEntity user = userService.getUser(username);
            log.info("doFilter", user.toString());
            System.out.println("do filtering ~~~~~~");
            if (Optional.ofNullable(user).isPresent() && Optional.ofNullable(user.getUsername()).isPresent()){
                //아래 과정이 끝나면 User 인증이 된 것임 
                //BCryptPasswordEncoder 는 시간에 따라 값이 변경 된다. 
                // SHA 방식등 다양한 인코딩 방식을 사용할  수 이싿. 
                //Pre 인증정보 확인 후 MFA 확인 
                log.info("Password Match ??? : " + ((BCryptPasswordEncoder)passwordEncoder).matches(password, user.getPassword()));
                log.info("DB password [{}] password from web [{}] ", user.getPassword(), password);
                if (((BCryptPasswordEncoder)passwordEncoder).matches(password, user.getPassword())){
                    httpServletRequest.getSession().setAttribute("username", username);
                    httpServletRequest.getSession().setAttribute("password", password);
                    MfaDto mfaDto = mfaService.getMfa(username);
                    if (Optional.ofNullable(mfaDto).isPresent() && Optional.ofNullable(mfaDto.getSecretKey()).isPresent()){
                        //otp 를 같이 보낸다 .
                        httpServletRequest.getSession().setAttribute("mfa", true);
                        httpServletResponse.sendRedirect("/mfactor");

                    } else {
                        //id password
                        httpServletRequest.getSession().setAttribute("mfa", false);
                        httpServletResponse.sendRedirect("/purelogin");

                    }
                } else {
                    httpServletResponse.sendRedirect("/logout");
                }
            } else {
                httpServletResponse.sendRedirect("/logout");
            }
        } else {
            //throw new ServletException();
            chain.doFilter(request, response);
        }
    }

}
