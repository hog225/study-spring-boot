package org.yg.study.JPAsample;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class OptionalTest {


    @Test
    public void testOptional(){
//        Integer age = 5;
//        Integer integer = Optional.ofNullable(age).
//                .filter(Objects::isNull).orElse(3);
//        System.out.println(integer);
        String a = null;
        String result = Optional.ofNullable(a).map(str -> str+" power").orElse("String a is NULL");
        assertThat(result).isEqualTo("String a is NULL");
        a = "power";
        result = Optional.ofNullable(a).map(str -> str+" power").orElse("String a is NULL");
        assertThat(result).isEqualTo("power power");
    }

    @Test
    public void orElse_VS_orElseGet(){

        String a = "power";//"";
        // null이든 아니든 호출되며 null이 아닌경우 앞에 갚 null인경우 입력한 값
        String result = Optional.ofNullable(a).orElse("null임");
        System.out.println(result);


        String b = "ㄹㄷㄹㄷ";//"not null";
        // 비용이 더 싸다
        String result1 = Optional.ofNullable(b).orElseGet(()->"null 임");
        System.out.println(result1);

    }

}
