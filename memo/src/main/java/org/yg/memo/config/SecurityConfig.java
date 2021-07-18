package org.yg.memo.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.yg.memo.security.handler.ClubLoginSuccessHandler;
import org.yg.memo.security.service.ClubUserDetailsService;

@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // Anotation으로 Authorize 경로를 설정
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ClubUserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //인가
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //EnableGlobalMethodSecurity 애노테이션이 있으면 Controller 에서 Role 을 기준으로 접근 제한을 세팅할수 있다.
//        http.authorizeRequests()
//                .antMatchers("/sample/all").permitAll()
//                .antMatchers("/sample/member").hasRole("USER");
        http.formLogin(); // 인증 인가 문제시 로그인 화면으로
        // 그 이외에도 loginPage, loginProcessUrl, defaultSuccessUrl, failureUrl

        http.csrf().disable();
        http.logout(); // csrf 토큰 미사용의 경우 get 방식으로 로그아웃/ 사용의 경우에는 Post 로 로그아웃 해야 함
        //Spring Security 는 HttpSession을 이용함

        // oauth enable
        http.oauth2Login().successHandler(successHandler());
        // remember me 기능 FormLogin 만 가능
        http.rememberMe().tokenValiditySeconds(60*60*24*7).userDetailsService(userDetailsService);


    }

    @Bean
    public ClubLoginSuccessHandler successHandler() {
        return new ClubLoginSuccessHandler(passwordEncoder());
    }

    //인증
    // AuthenticationManagerBuilder 는 UserDetailService 를 이용하여 인증처리를 한다. ClubUserDetailService를 만들어 줬음으로
    // 아래 코드는 필요 없다.
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication().withUser("user1")
//                .password("$2a$10$3vVhlTwYne0UfW6CCCtOiOJS5rcTDWP7a8ekGTT.9dgrTDgJVZVuy")
//                .roles("USER");
//    }

}
