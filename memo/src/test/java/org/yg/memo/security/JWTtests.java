package org.yg.memo.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yg.memo.security.util.JWTUtil;

public class JWTtests {
    private JWTUtil jwtUtil;

    @BeforeEach
    public void testBefore(){
        System.out.println("test before ..........");
        jwtUtil = new JWTUtil();
    }

    @Test
    public void testEncode() throws Exception{
        String email = "yg@naver.com";
        String str = jwtUtil.generateToken(email);
        System.out.println(str);
    }
    //검증은 https://jwt.io 에서도 가능

    @Test
    public void testValidate() throws Exception{
        String email = "yg@naver.com";
        String str = jwtUtil.generateToken(email);

        Thread.sleep(5000);

        String resultEmail = jwtUtil.validateAndExtract(str);
        System.out.println(resultEmail);
    }
}
