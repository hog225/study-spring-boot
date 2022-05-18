package org.yg.study.JPAsample.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String name;
    private int age;

    // 요걸쓰면 DTO 가 QueryDSL 의존성을 같게됨
    // 경계 분리가 제대로 되지 않기 때문에 별로 좋진 않음
    @QueryProjection
    public UserDto(String name, int age){
        this.name = name;
        this.age = age;
    }

}
