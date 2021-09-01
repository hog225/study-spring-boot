package org.yg.study.JPAsample.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.dto.MemberDto;
import org.yg.study.JPAsample.entity.Member;
import org.yg.study.JPAsample.entity.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {
    
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;
    
    @Test
    public void testMember(){
        System.out.println("memberRepository = " + memberRepository);
        Member member = new Member("YGMEMBERB");
        Member savedMember = memberRepository.save(member);

        Member member1 = memberRepository.findById(savedMember.getId()).get();
        assertThat(member1.getId()).isEqualTo(savedMember.getId());
        assertThat(member1.getUsername()).isEqualTo(savedMember.getUsername());
        // 같은 Transaction 임으로 member1 과 SavedMember는 같다.
        assertThat(member1).isEqualTo(savedMember);
    }

    @Test
    public void findByUsernameAndAgeGreaterThenTest(){
        Member m1 = new Member("bbbb", 10);
        Member m2 = new Member("bbbb", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("bbbb", 15);
        assertThat(result.get(0).getUsername()).isEqualTo("bbbb");
        assertThat(result.size()).isEqualTo(1);

    }

    @Test
    public void findByUsernameIgnoreCaseTest(){
        Member m1 = new Member("aaaa", 10);
        Member m2 = new Member("aAaA", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameIgnoreCase("aaaa");
        assertThat(result.get(0).getUsername()).isEqualTo("aaaa");
        assertThat(result.size()).isEqualTo(2);

    }
    @Test
    public void findByUsernameNamedQuery(){
        Member m1 = new Member("aaaa", 10);
        Member m2 = new Member("aAaA", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsername("aaaa");
        assertThat(result.get(0).getUsername()).isEqualTo("aaaa");
        assertThat(result.size()).isEqualTo(1);

    }

    @Test
    public void findMemberDtoTest(){
        Team team = new Team("ATeam");
        teamRepository.save(team);

        Member m1 = new Member("aaaa", 10);
        m1.setTeam(team);


        Member m2 = new Member("aAaA", 20);
        m2.setTeam(team);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for (MemberDto dto : memberDto) {
            System.out.println("dto = " + dto);
        }


    }

    @Test
    public void findMemberDtoTest2(){
        Team team = new Team("ATeam");
        teamRepository.save(team);

        Member m1 = new Member("aaaa", 10);
        m1.setTeam(team);


        Member m2 = new Member("aAaA", 20);
        m2.setTeam(team);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Object[]> memberDto = memberRepository.findMemberDto2();
        for (Object[] objects : memberDto) {
            for (Object object : objects) {
                System.out.println(object);
            }
        }

    }

    @Test
    public void findBynames(){
        Member m1 = new Member("aaaa", 10);
        Member m2 = new Member("aAaA", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByNames(Arrays.asList("aaaa", "aAaA"));
        assertThat(result.size()).isEqualTo(2);

    }
}