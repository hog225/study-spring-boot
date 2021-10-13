package org.yg.practice.security.configures.filter;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {

    private final String Authority;

    @Override
    public String getAuthority() {
        return this.Authority;
    }
}
