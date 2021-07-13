package org.yg.memo.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //인가
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/sample/all").permitAll()
                .antMatchers("/sample/member").hasRole("USER");
        http.formLogin(); // 인증 인가 문제시 로그인 화면으로
        // 그 이외에도 loginPage, loginProcessUrl, defaultSuccessUrl, failureUrl

        http.csrf().disable();
        http.logout(); // csrf 토큰 미사용의 경우 get 방식으로 로그아웃/ 사용의 경우에는 Post 로 로그아웃 해야 함
        //Spring Security 는 HttpSession을 이용함

    }

    //인증
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("user1")
                .password("$2a$10$3vVhlTwYne0UfW6CCCtOiOJS5rcTDWP7a8ekGTT.9dgrTDgJVZVuy")
                .roles("USER");
    }

}
