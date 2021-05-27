package org.yg.practice.flyway.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// 일종의 DTO Model 에 있는것 보단 DTO, VO 폴더를 만들어서 거기에 위치 시키는게 좋을 듯 하다. 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {
    private String resultCode;
    private T data;

    public static <T> Header<T> OK(){
        return (Header<T>) Header.builder().resultCode("OK").build();
    }

    public static <T> Header<T> OK(T data){
        return (Header<T>) Header.builder().resultCode("OK").data(data).build();
    }

    public static <T> Header<T> ERROR(){
        return (Header<T>) Header.builder().resultCode("ERROR").build();
    }
}
