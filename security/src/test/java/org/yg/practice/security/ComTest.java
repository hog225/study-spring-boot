package org.yg.practice.security;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ComTest {

    @Test
    public void BCryptPasswordEncoderTest(){
        BCryptPasswordEncoder passwdEncoder = new BCryptPasswordEncoder();
        String encoded = passwdEncoder.encode("security!#34");
        System.out.println(encoded);
        boolean result = passwdEncoder.matches("security!#34", "$2a$10$15oAo5ObvO9YRimyxUMzROMt2Ga73mXDp9D/Ern6w6gE50cmf/9IG");
        System.out.println(result);
        
    }
    
}
