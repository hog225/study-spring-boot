package org.yg.study.JPAsample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.entity.Member;
import org.yg.study.JPAsample.entity.Team;
import org.yg.study.JPAsample.repository.MemberRepository;
import org.yg.study.JPAsample.repository.TeamRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberTeamService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void save(int i) {

        memberRepository.save(new Member("memberA" + i));
        teamRepository.save(new Team("teamA" + i));

    }

    @Transactional
    public void createTeamAndMember(Team team, List<Member> members) {
        teamRepository.save(team);
        memberRepository.saveAll(members);
    }

    public List<Member> getMembersByTeam(Team team) {
        return memberRepository.findByTeam(team);
    }

    @Transactional
    public void updateMember(Team team, List<Member> members) {
        team.setMembers(members);
        teamRepository.save(team);
    }

}
