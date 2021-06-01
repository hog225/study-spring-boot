package org.yg.practice.security.configures;

import org.yg.practice.security.configures.filters.OpenStackFilter;
import org.yg.practice.security.configures.handler.OpenStackAuthenticationFailure;
import org.yg.practice.security.configures.handler.OpenStackAuthenticationSuccess;
import org.yg.practice.security.configures.provider.OpenStackTokenAuthProvider;
import org.openstack4j.api.OSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final OSClient.OSClientV3 adminOsClient;

    @Autowired
    public WebSecurityConfiguration(OSClient.OSClientV3 adminOsClient) {
        this.adminOsClient = adminOsClient;
    }

    //HTTP 보안의 관점을  설정하기 위함 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(getOpenStackFilter(), UsernamePasswordAuthenticationFilter.class);
        http
                .headers().frameOptions().sameOrigin()
                .and().formLogin().loginPage("/login")
                .and().logout().logoutUrl("/logout") // logout을 지원하고 logout url을 매핑
                .and().authorizeRequests().antMatchers("/login", "/").permitAll() // root 나 /login page는 인증없이 허용됨 
                .and().authorizeRequests().antMatchers("/*", "/**/*").authenticated(); // 모든 Request는 인증되어야 함을 의미 
        http.csrf().disable();
    }

    private OpenStackFilter getOpenStackFilter() throws Exception {
        OpenStackFilter openStackFilter = new OpenStackFilter(this.authenticationManager());
        openStackFilter.setAuthenticationSuccessHandler(new OpenStackAuthenticationSuccess());
        openStackFilter.setAuthenticationFailureHandler(new OpenStackAuthenticationFailure());
        return openStackFilter;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring() // 인증을 타지 않아도 해당 Resource에 접근이 가능하도록 하게 하는 기능 
                .antMatchers("/i18n/**")
                .antMatchers("/static/**")
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/images/**");
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new OpenStackTokenAuthProvider(adminOsClient));
    }
}
