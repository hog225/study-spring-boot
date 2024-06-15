package org.yg.study.JPAsample.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.yg.study.JPAsample.service.MemberTeamService;

import javax.annotation.PostConstruct;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitMember {

    private final InitMemberService initMemberService;

    @PostConstruct
    public void init(){
        initMemberService.init();
    }

//    @Component
//    static class InitMemberService {
//        @PersistenceContext
//        private EntityManager em;
//
//        @Transactional
//        public void init(){
//            Team teamA = new Team("teamA");
//            Team teamB = new Team("teamB");
//            em.persist(teamA);
//            em.persist(teamB);
//            for (int i=0; i< 2; i++){
//                Team selectedTeam = i % 2 == 0 ? teamA : teamB;
//                em.persist(new Member("member"+i, i, selectedTeam));
//
//            }
//        }
//    }

    @Component
    @RequiredArgsConstructor
    static class InitMemberService {
        private final MemberTeamService memberTeamService;

        public void init(){

            for (int i=0; i< 3; i++) {
//                memberTeamService.save(i);
            }
        }





    }




}
