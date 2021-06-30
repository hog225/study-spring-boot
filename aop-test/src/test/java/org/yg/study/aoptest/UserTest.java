package org.yg.study.aoptest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
public class UserTest {
    @Test
    public void testUser() {


        //given
        User user = new User();
        //when
        //then
        assertThat(user.greeting()).isEqualTo("hello");

    }
}
