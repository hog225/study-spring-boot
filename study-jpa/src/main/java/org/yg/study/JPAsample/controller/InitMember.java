package org.yg.study.JPAsample.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.entity.Member;
import org.yg.study.JPAsample.entity.Team;
import org.yg.study.JPAsample.repository.MemberRepository;
import org.yg.study.JPAsample.repository.TeamRepository;
import org.yg.study.JPAsample.service.MemberTeamSave;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        private final MemberTeamSave memberTeamSave;

        public void init(){

            for (int i=0; i< 3; i++) {
                memberTeamSave.save(i);
            }
        }





    }




}
