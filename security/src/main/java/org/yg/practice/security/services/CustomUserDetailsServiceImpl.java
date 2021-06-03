package org.yg.practice.security.services;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.yg.practice.security.datas.entities.UserEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.yg.practice.security.datas.dto.CustomUserDetails;
import org.yg.practice.security.exception.OtpNotProveException;
import org.yg.practice.security.util.OTPUtil;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService{
    private final UserService userService;
    private final MfaService mfaService;
    private String otp;

    @Autowired
    public CustomUserDetailsServiceImpl(UserService userService, MfaService mfaService){
        this.userService =userService;
        this.mfaService = mfaService;
    }

    @Override
    public UserDetails loadUserByUsername(String username, String otp) throws UsernameNotFoundException, OtpNotProveException {
        this.otp = otp;
        if (otp != null){
            String secretKey = mfaService.getMfaSecretKey(username).getSecretKey();
            if (!OTPUtil.checkCode(otp, secretKey)){
                throw new OtpNotProveException("OTP number didn't approve. please check again");
            }
        }
        return loadUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.getUser(username);
        if (user == null){
            throw new UsernameNotFoundException("the user not exist");

        }
        CustomUserDetails.CustomUserDetailsBuilder customUserDetailsBuilder = CustomUserDetails.builder();
        customUserDetailsBuilder.username(user.getUsername())
            .password(user.getPassword())
            .authorities(Arrays.stream(user.getRoles().split(",")).map(x-> new SimpleGrantedAuthority(x)).collect(Collectors.toList()))
            .accountNonExpired(true)
            .accountNonLocked(true)
            .credentialsNonExpired(true)
            .enabled(true);

        return customUserDetailsBuilder.build();
    }


    
}
