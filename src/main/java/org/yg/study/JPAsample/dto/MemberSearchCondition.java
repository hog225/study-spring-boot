package org.yg.study.JPAsample.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberSearchCondition {
    //회원명, 팀명, 나이(ageGoe, ageLoe)
    private String username;
    private String teamName;
    private Integer ageGoe;
    private Integer ageLoe;
}
