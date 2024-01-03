package org.yg.study.JPAsample.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yg.study.JPAsample.entity.Member;
import org.yg.study.JPAsample.entity.Team;
import org.yg.study.JPAsample.repository.MemberRepository;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class MemberTeamServiceTest {
    @Autowired
    MemberTeamService memberTeamService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("팀과 맴버를 생성 후 팀 Entity 의 List 를 변경하면 변경사항이 Member 에 자동으로 반영된다. ")
    void createTeamMember() {
        var team = new Team("teamA");
        var members = IntStream.range(1, 10).mapToObj(i -> {
            return new Member("member" + i, i, team);
        }).collect(Collectors.toList());
        memberTeamService.createTeamAndMember(team, members);

        var members1 = memberTeamService.getMembersByTeam(team);
        members1.forEach(System.out::println);
        // 일부 선택

        var partMembers = members1.stream().filter(m -> m.getAge() <=2).collect(Collectors.toList());

        // team Entity 에 변화를 주면 변경 사항이 자동으로 반영 되서 member db 가 삭제 되거나 추가 된다. 다만 쿼리가 변경되 member 수만큼 나간다 .
        memberTeamService.updateMember(team, partMembers);

        var members2 = memberTeamService.getMembersByTeam(team);
        members2.forEach(System.out::println);

    }

}