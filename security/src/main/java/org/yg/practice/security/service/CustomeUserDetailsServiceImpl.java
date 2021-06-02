package org.yg.practice.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.yg.practice.security.datas.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.yg.practice.security.datas.dto.CustomUserDetails;
import org.yg.practice.security.exception.OtpNotApproveException;
import org.yg.practice.security.util.OTPUtil;

public class CustomeUserDetailsServiceImpl implements CustomUserDetailsService{
    private final UserService userService;
    private final MfaService mfaService;
    private String otp;

    @Autowired
    public CustomeUserDetailsServiceImpl(UserService userService, MfaService mfaService){
        this.userService =userService;
        this.mfaService = mfaService;
    }

    @Override
    public UserDetails loadUserByUsername(String username, String otp) throws UsernameNotFoundException, OtpNotApproveException {
        this.otp = otp;
        if (otp != null){
            String secretKey = mfaService.getMfaSecretKey(username).getSecretKey();
            if (!OTPUtil.checkCode(otp, secretKey)){
                throw new OtpNotApproveException("OTP number didn't approve. please check again");
            }
        }
        return loadUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUser(username);
        if (user == null){
            throw new UsernameNotFoundException("the user not exist");

        }
        CustomUserDetails.CustomUserDetailsBuilder customUserDetailsBuilder = CustomUserDetails.builder();
        customUserDetailsBuilder.username(user.getUsername())
            .password(user.getPassword())
            .accountNonExpired(false)
            .accountNonLocked(true)
            .credentialsNonExpired(false)
            .enabled(true);

        return customUserDetailsBuilder.build();
    }


    
}
