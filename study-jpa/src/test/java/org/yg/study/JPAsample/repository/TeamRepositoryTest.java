package org.yg.study.JPAsample.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.config.TestConfig;
import org.yg.study.JPAsample.entity.Member;
import org.yg.study.JPAsample.entity.Team;
import org.yg.study.JPAsample.entity.Unit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
@Import(TestConfig.class)
class TeamRepositoryTest {


    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    private Team team;
    private List<Member> members;

    private List<Unit> units;

    @BeforeEach
    void setUp() {
        team = new Team("teamA");
        members = IntStream.range(1, 10).mapToObj(i -> {
            return Member.builder()
                    .username("33")
                    .team(team)
                    .build();
        }).collect(Collectors.toList());


        units = IntStream.range(1, 10).mapToObj(i -> {
            return Unit.builder()
                    .name("33")
                    .team(team)
                    .build();
        }).collect(Collectors.toList());

        team.setMembers(members);
        team.setUnits(units);
        teamRepository.save(team);

        em.flush();
        em.clear();
    }


    @Test
    void teamRepositoryTest() {


        // when
        Team findTeam = teamRepository.findById(team.getId()).get();
        List<Member> members1 = memberRepository.findByTeam(findTeam);


        System.out.println(findTeam);
        members1.forEach(System.out::println);

    }

    @Test
    @DisplayName("fetch 테스트 BatchSize 는 Eager 로 가져올 때 N + 1 문제를 해결하기 위해 사용한다.")
    void teamRepositoryTest2() {
        IntStream.range(1, 5).forEach(i -> {
            var team = new Team("team-" + i);
            List<Member> members = IntStream.range(1, 2).mapToObj(index -> {
                return Member.builder()
                        .username("33" + index)
                        .team(team)
                        .build();
            }).collect(Collectors.toList());


            List<Unit> units = IntStream.range(1, 2).mapToObj(index -> {
                return Unit.builder()
                        .name("unit" + index)
                        .team(team)
                        .build();
            }).collect(Collectors.toList());

            team.setMembers(members);
            team.setUnits(units);
            teamRepository.save(team);
        });
        em.flush();
        em.clear();

        var teams = teamRepository.findTeams();
        System.out.println("=====================================");
        for (Team team : teams) {
            System.out.println("team = " + team);
            team.getUnits().forEach(System.out::println);
        }

    }



}