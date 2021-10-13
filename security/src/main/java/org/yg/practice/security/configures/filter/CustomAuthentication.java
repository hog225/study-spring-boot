package org.yg.practice.security.configures.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.yg.practice.security.configures.Authorities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Log4j2
@Configuration
public class CustomAuthentication implements Authentication {

    CustomAuthentication(){
        log.info("CustomAuthentication");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        CustomGrantedAuthority myAuth = new CustomGrantedAuthority(Authorities.ADMIN_LV1.name());
        List<CustomGrantedAuthority> authorities= new ArrayList<>();
        authorities.add(myAuth);

        log.info("Auth ~~~~~~~~~~~~~~~~~~~~~~");
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
