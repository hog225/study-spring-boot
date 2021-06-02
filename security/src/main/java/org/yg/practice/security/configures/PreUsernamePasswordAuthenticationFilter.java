package org.yg.practice.security.configures;

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
import org.yg.practice.security.datas.entities.User;
import org.yg.practice.security.service.MfaService;
import org.yg.practice.security.service.UserService;

//실제 인증을 타기 전에 해당 사용자가 맞는지 아닌지 사용자가 DTO 가 설정 되어 있는지 아닌지 판단하여 어느 화면으로 이동하여 작업할지를 결정하는 
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
        // prelogin post 에 대해 처리 하겠다. 
        if (httpServletRequest.getServletPath().equals("/prelogin") && httpServletRequest.getMethod().equals("POST")){
            String username = httpServletRequest.getParameter("username");
            String password = httpServletRequest.getParameter("password");
            User user = userService.getUser(username);

            if (Optional.ofNullable(user).isPresent() && Optional.ofNullable(user.getUsername()).isPresent()){
                //아래 과정이 끝나면 User 인증이 된 것임 
                //BCryptPasswordEncoder 는 시간에 따라 값이 변경 된다. 
                if (((BCryptPasswordEncoder)passwordEncoder).matches(password, user.getPassword())){

                }
            }
        }
    }

}
