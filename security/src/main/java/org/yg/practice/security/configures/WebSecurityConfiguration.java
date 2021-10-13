package org.yg.practice.security.configures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.yg.practice.security.configures.filter.CustomAuthenticationFilter;
import org.yg.practice.security.configures.filter.CustomUsernamePasswordAuthenticationFilter;
import org.yg.practice.security.configures.filter.PreUsernamePasswordAuthenticationFilter;
import org.yg.practice.security.configures.provider.CustomDaoAuthenticationProvider;
import org.yg.practice.security.configures.handler.FailureHandler;
import org.yg.practice.security.configures.handler.LogoutSucceedHandler;
import org.yg.practice.security.configures.handler.SuccessHandler;
import org.yg.practice.security.services.UserService;

import lombok.extern.slf4j.Slf4j;

import org.yg.practice.security.services.CustomUserDetailsService;
import org.yg.practice.security.services.MfaService;

@Slf4j
@Configuration
@EnableWebSecurity
@Order(1)
// 아래 방식으로 하면 repository에 @Repository 애노테이션을 안 붙혀도 된다. 
//@EnableJpaRepositories(basePackages = {"org.yg.practice.security"})
// 만약 메인 Package 외부에 Entity가 있다면 아래 애노테이션을 써서 Bean으로 등록 해줘야 함 
//@EntityScan(basePackage = {"org.yg.practice.security"}, basePackageClassed = {Jsr310Converters.class})
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
    private String permitalURL = "/login,/,/mfactor,/purelogin,/prelogin";
    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService; 
    private final MfaService mfaService;


    @Autowired
    public WebSecurityConfiguration(CustomUserDetailsService customUserDetailsService, UserService userService, MfaService mfaService){
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
        this.mfaService = mfaService;
    }



    @Bean 
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // 어떻게 이용자 정보를 제공 할거냐에 대한 내용 In-memory, Oauth, JDBC 등등
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser(username)
        auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder());

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

// Login
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("configure Filter ");
        // UsernamePasswordAuthenticationFilter 앞에 PreUsernamePasswordAuthenticationFilter 를 넣어서 필터를 수행 하라
        http.addFilterBefore(new PreUsernamePasswordAuthenticationFilter(bCryptPasswordEncoder(), userService, mfaService), UsernamePasswordAuthenticationFilter.class);
        // UsernamePasswordAuthenticationFilter 단계에서 CustomUsernamePasswordAuthenticationFilter 가 호출된다.
        http.addFilterAt(customUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        //.addFilterAfter(customAuthenticationFilter(), CustomUsernamePasswordAuthenticationFilter.class);

        http.authenticationProvider(customDaoAuthenticationProvider());
        http.cors()
            .and().headers().frameOptions().sameOrigin() // url 의 도메인 보는
            //.csrf().disable();

            // 인증하지 않아도 접근할 수 있는 URL
            .and().authorizeRequests().antMatchers(permitalURL.split(",")).permitAll()
            .and().formLogin().loginPage("/login").successHandler(new SuccessHandler()).failureHandler(new FailureHandler()) // get 이 오면 화면 post가 오면 filter 가 캐치
            .and().logout().logoutUrl("/logout").logoutSuccessHandler(new LogoutSucceedHandler()).invalidateHttpSession(false).permitAll()
            .and().authorizeRequests().anyRequest().authenticated();//위의 Request 가 아닌 모든 Request는 인증이 필요하다.
        // super.configure(http);
        http.csrf().disable(); // 이것 때문에 한 한 두시간 날림
        // 등록된 Confiruration이 안맞으면 /error를 Filter쪽으로 주는데 처리를 안해서 인지 원인 알기가 어려웠음
    }


    private CustomAuthenticationFilter customAuthenticationFilter() throws Exception{
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter();
        return customAuthenticationFilter;
    }

    private CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() throws Exception{
        CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter
            = new CustomUsernamePasswordAuthenticationFilter(this.authenticationManagerBean());
        customUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(new SuccessHandler());
        customUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(new FailureHandler());
        
        return customUsernamePasswordAuthenticationFilter;
    }

    private CustomDaoAuthenticationProvider customDaoAuthenticationProvider(){
        CustomDaoAuthenticationProvider customDaoAuthenticationProvider = new CustomDaoAuthenticationProvider(customUserDetailsService);
        customDaoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return customDaoAuthenticationProvider;

    }


}
