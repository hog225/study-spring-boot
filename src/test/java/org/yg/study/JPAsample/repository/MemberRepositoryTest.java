package org.yg.study.JPAsample.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.dto.MemberDto;
import org.yg.study.JPAsample.dto.MemberSearchCondition;
import org.yg.study.JPAsample.dto.MemberTeamDto;
import org.yg.study.JPAsample.entity.Member;
import org.yg.study.JPAsample.entity.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {
    
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @PersistenceContext
    EntityManager em;

    @BeforeEach
    public void beforeEach() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

    }


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

    @Test
    public void pagin(){
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        // Sorting 이 복잡하면 JPQL 로 
        Pageable pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));
 
        Page<Member> page = memberRepository.findByAge(age, pageRequest);
        
        // API 반환시 아래를 반환 
        Page<MemberDto> memberdto = page.map(member -> new MemberDto(member.getId(), member.getUsername(), member.getTeam().getName()));

        List<Member> content = page.getContent();
        long totalElements = page.getTotalElements();
        
        for (Member member: content){
            System.out.println(member);
        }

        assertThat(content.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
        
    }

    @Test
    public void bulkUpdate(){
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 19));
        memberRepository.save(new Member("member3", 20));
        memberRepository.save(new Member("member4", 21));
        memberRepository.save(new Member("member5", 40));

        // update 쿼리는 DB에만 적용할뿐 영속성 컨텍스트는 그내용을 모른다. 
        // 쿼리후에는 flush, clear 명령으로 영속성 컨텍스트를 날려야 한다. 
        // 혹은 Modifying 애노테이션에 옵션을 준다. clearAutomatically
        int resultCount = memberRepository.bulkAgePluse(20);
        
        // em.flush();
        // em.clear();


        List<Member> mem = memberRepository.findByUsername("member5");
        System.out.println(mem.get(0));
        assertThat(resultCount).isEqualTo(3);

    }

    @Test
    public void fetchJoinTest(){
        Team teamA = new Team("ATeam");
        Team teamB = new Team("BTeam");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member m1 = new Member("aaaa", 10, teamA);
        Member m2 = new Member("bbbb", 10, teamB);


        memberRepository.save(m1);
        memberRepository.save(m2);

        em.flush();
        em.clear();


        //List<Member> members = memberRepository.findAll();
        //List<Member> members = memberRepository.findMemberFetchJoin();
        // 아래 EntityGraph 적용된 쿼리 
        List<Member> members = memberRepository.findAll();
        for (Member mem : members) {
            System.out.println("mem = " + mem.getUsername());
            System.out.println("mem.team.class = " + mem.getTeam().getClass());
            System.out.println("mem.team = " + mem.getTeam().getName());
        }


    }

    @Test
    public void queryHint() {
        Member m1 = memberRepository.save(new Member("member1", 10));
        em.flush();
        em.clear();

        // readOnly 로 했는데 update 가 됨 .. 원인은 ?
        // Member findMember = memberRepository.findById(m1.getId()).get();
        Member findMember = memberRepository.findReadOnlyByUsername("member1");
        findMember.setUsername("member2");

        em.flush();
    }


    // @Test
    // public void queryLock() {
    //     Member m1 = memberRepository.save(new Member("member1", 10));
    //     em.flush();
    //     em.clear();

    //     // readOnly 로 했는데 update 가 됨 .. 원인은 ?
    //     // Member findMember = memberRepository.findById(m1.getId()).get();
    //     List<Member> findMember = memberRepository.findLockByUserName("member1");
    //     System.out.println(findMember.get(0));
    // }

    @Test
    public void callCustom(){
        List<Member> result = memberRepository.findMemberCustom();
    }

    @Test
    public void TestBaseEntity() throws Exception {
      //given
          Member member = new Member("member1");
          memberRepository.save(member); //@PrePersist
          Thread.sleep(100);
          member.setUsername("member2");
          em.flush();
          em.clear();

      //when
          Member findMember = memberRepository.findById(member.getId()).get();
      //then
          System.out.println("findMember.createdDate = " + findMember.getCreatedDate());
          System.out.println("findMember.updatedDate = " + findMember.getLastModifiedDate());
          System.out.println("findMember.getCreatedBy = " + findMember.getCreatedBy());
          System.out.println("findMember.getLasteModifiedBy = " + findMember.getLasteModifiedBy());
    }

    @Test
    public void QueryDslMemberSearch(){

        List<Member> members = memberRepository.searchMemberAll();

    }

    @Test
    public void QueryDslSearch(){
        MemberSearchCondition cond = MemberSearchCondition.builder()
                .build();
        List<MemberTeamDto> memberTeamDtos = memberRepository.search(cond);

        for (MemberTeamDto member : memberTeamDtos){
            System.out.println(member);
        }
    }

    @Test
    public void queryDslSearchSimple(){
        MemberSearchCondition cond = MemberSearchCondition.builder()
                .build();
        Pageable pageable = PageRequest.of(0, 2);
        Page<MemberTeamDto> memberTeamDtos = memberRepository.searchSimple(cond, pageable);

        System.out.println(memberTeamDtos.getSize());
        System.out.println(memberTeamDtos.getContent());
    }

}
