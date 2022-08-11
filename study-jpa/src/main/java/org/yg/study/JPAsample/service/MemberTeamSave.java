package org.yg.study.JPAsample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.entity.Member;
import org.yg.study.JPAsample.entity.Team;
import org.yg.study.JPAsample.repository.MemberRepository;
import org.yg.study.JPAsample.repository.TeamRepository;

@Service
@RequiredArgsConstructor
public class MemberTeamSave {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void save(int i) {

        memberRepository.save(new Member("memberA" + i));
        teamRepository.save(new Team("teamA" + i));

    }

}
