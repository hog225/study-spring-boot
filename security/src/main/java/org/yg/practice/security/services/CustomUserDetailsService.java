package org.yg.practice.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.yg.practice.security.exception.OtpNotProveException;

public interface CustomUserDetailsService extends UserDetailsService {
    UserDetails loadUserByUsername(String username, String otp) throws UsernameNotFoundException, OtpNotProveException;
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}