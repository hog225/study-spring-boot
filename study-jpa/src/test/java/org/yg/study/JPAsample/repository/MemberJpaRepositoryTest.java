package org.yg.study.JPAsample.repository;

import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.entity.Member;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberJpaRepositoryTest {
    
    @Autowired
    MemberJpaRepository memberJpaRepository;
    
    @PersistenceContext
    EntityManager em;
    
    @Test
    public void testMember(){
        Member member = new Member("YGMEMBER");
        Member savedMember = memberJpaRepository.save(member);

        Member member1 = memberJpaRepository.find(savedMember.getId());
        assertThat(member1.getId()).isEqualTo(savedMember.getId());
        assertThat(member1.getUsername()).isEqualTo(savedMember.getUsername());
        // 같은 Transaction 임으로 member1 과 SavedMember는 같다.
        assertThat(member1).isEqualTo(savedMember);
    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        // dirty checking: Proxy 가 @Transactional 에 의 Commit 되는데 변경을 감지해서 알아서 Update 쿼리를 날린다.
//        member1.setUsername("Member!!!!!!!!!!!!");
//        List<Member> allData = memberJpaRepository.findAll();
//
//        for (Member member: allData){
//            System.out.println(member);
//        }
        /////////////////////////////////////
        //단건 조회 검증
        Member findMember1 =
                memberJpaRepository.findById(member1.getId()).get();
        Member findMember2 =
                memberJpaRepository.findById(member2.getId()).get();
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);
        //리스트 조회 검증
        List<Member> all = memberJpaRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        //카운트 검증
        long count = memberJpaRepository.count();
        assertThat(count).isEqualTo(2);
        //삭제 검증
        memberJpaRepository.delete(member1);
        memberJpaRepository.delete(member2);
        long deletedCount = memberJpaRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }

    @Test
    public void pagin(){
        //given
        memberJpaRepository.save(new Member("member1", 10));
        memberJpaRepository.save(new Member("member2", 10));
        memberJpaRepository.save(new Member("member3", 10));
        memberJpaRepository.save(new Member("member4", 10));
        memberJpaRepository.save(new Member("member5", 10));
        int age = 10;
        int offset = 1;
        int limit = 3;
        //when
        List<Member> members = memberJpaRepository.findByAgeUsingPage(age, offset, limit);
        long totalCount = memberJpaRepository.totalCount(age);
        //페이지 계산 공식 적용...
        // totalPage = totalCount / size ...
        // 마지막 페이지 ...
        // 최초 페이지 ..
        //then
        assertThat(members.size()).isEqualTo(3);
        assertThat(totalCount).isEqualTo(5);
    }

    @Test
    public void bulkUpdate(){
        memberJpaRepository.save(new Member("member1", 10));
        memberJpaRepository.save(new Member("member2", 19));
        memberJpaRepository.save(new Member("member3", 20));
        memberJpaRepository.save(new Member("member4", 21));
        memberJpaRepository.save(new Member("member5", 40));

        int resultCount = memberJpaRepository.bulkAgePluse(20);
        assertThat(resultCount).isEqualTo(3);

    }

    @Test
    public void JpaEventBaseEntity() throws Exception {
      //given
          Member member = new Member("member1");
          memberJpaRepository.save(member); //@PrePersist
          Thread.sleep(100);
          member.setUsername("member2");
          em.flush();
          em.clear();

      //when
          Member findMember = memberJpaRepository.findById(member.getId()).get();
      //then
          System.out.println("findMember.createdDate = " +
      findMember.getCreatedDate());
          System.out.println("findMember.updatedDate = " +
      findMember.getLastModifiedDate());
    }
}