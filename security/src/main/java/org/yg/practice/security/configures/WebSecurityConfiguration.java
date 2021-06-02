package org.yg.practice.security.configures;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
// 아래 방식으로 하면 repository에 @Repository 애노테이션을 안 붙혀도 된다. 
//@EnableJpaRepositories(basePackages = {"org.yg.practice.security"})
// 만약 메인 Package 외부에 Entity가 있다면 아래 애노테이션을 써서 Bean으로 등록 해줘야 함 
//@EntityScan(basePackage = {"org.yg.practice.security"}, basePackageClassed = {Jsr310Converters.class})
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
    private String permitalURL = "/login,/,/mfator,/purelogin";
    // 어떻게 이용자 정보를 제공 할거냐에 대한 내용 In-memory, Oauth, JDBC 등등
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser(username)
        super.configure(auth);

    }

    // 인증을 무시할 PATH
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/i18n/**")
            .antMatchers("/static/**")
            .antMatchers("/css/**")
            .antMatchers("/js/**")
            .antMatchers("/images/**");

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    
        http.cors().and()
            .csrf().disable();
        
        http.headers().frameOptions().sameOrigin() // url 의 도메인 보는 
        // 인증하지 않아도 접근할 수 있는 URL
            .and().authorizeRequests().antMatchers(permitalURL.split(",")).permitAll()
            .and().formLogin().loginPage("/login") // get 이 오면 화면 post가 오면 filter 가 캐치
            .and().logout().logoutUrl("/logout").invalidateHttpSession(false).permitAll()
            .and().authorizeRequests().anyRequest().authenticated();//위의 Request 가 아닌 모든 Request는 인증이 필요하다. 
        super.configure(http);
    }
}
