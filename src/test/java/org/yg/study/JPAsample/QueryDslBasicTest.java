package org.yg.study.JPAsample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.NonUniqueResultException;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.entity.*;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.yg.study.JPAsample.entity.QMember.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QueryDslBasicTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    public void beforeEach() {
        jpaQueryFactory = new JPAQueryFactory(em);
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
    void queryDslTest() {
        Hello hello = new Hello();

        em.persist(hello);
        JPAQueryFactory query = new JPAQueryFactory(em);
        QHello qHello = QHello.hello;

        Hello result = query.selectFrom(qHello).fetchOne();

        assertThat(result).isEqualTo(hello);
        assertThat(result.getId()).isEqualTo(hello.getId());


    }

    @Test
    public void startJPQL(){
        //
        Member member = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(member.getUsername()).isEqualTo("member1");

    }

    @Test
    public void startQueryDsl(){

        //alias 같은 테이블 조인  할때
        // QMember = new QMember("m")

        Member myMember = jpaQueryFactory
                .select(member)
                .from(member)
                .where(member.username.like("member1%"))
                .fetchOne();
        assert myMember != null;
        assertThat(myMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void search(){
        List<Member> member1 = jpaQueryFactory
                .selectFrom(member)
                .where(member.username.eq("member1")
                        .and(member.age.eq(10)))
                .fetch();

        assertThat(member1.get(0).getUsername()).isEqualTo("member1");

    }

    @Test
    public void fetchFirstTest(){
        // limit 1 이 나감
        Member member1 = jpaQueryFactory
                .selectFrom(member)
                .fetchFirst();

        System.out.println(member1);

    }

    @Test
    public void fetchOne(){

        // exception
        assertThatExceptionOfType(NonUniqueResultException.class).isThrownBy(()->{
            Member myMember = jpaQueryFactory
                    .selectFrom(member)
                    .fetchOne();
        });
    }

    @Test
    public void fetchCount(){
        // count query
        long cnt = jpaQueryFactory
                .selectFrom(member)
                .fetchCount();

        assertThat(cnt).isEqualTo(4);

    }

    @Test
    public void fetchMember(){
        // Query 가 두번 나감
        QueryResults<Member> memberQueryResults = jpaQueryFactory
                .selectFrom(member)
                .fetchResults();

        System.out.println(memberQueryResults.getResults());
        System.out.println(memberQueryResults.getLimit());
    }
    /**
     * 1 .회원 나이 냉림차순 desc
     * 2. 회원 이름 올림차순 asc
     * 회원 이름 없으면 마지막에 출력 nulls last
     *
     */
    @Test
    public void sort() throws JsonProcessingException {
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));

        // member5
        //member 6
        //null
        //

        List<Member> members = jpaQueryFactory
                .selectFrom(member)
                .orderBy(member.age.desc().nullsFirst(), member.username.asc().nullsLast())
                .fetch();

        ObjectMapper obm = new ObjectMapper();
        obm.findAndRegisterModules();
        String jsonMembers = obm.writerWithDefaultPrettyPrinter().writeValueAsString(members);
        System.out.println(jsonMembers);


        assertThat(members.get(0).getUsername()).isEqualTo("member5");


    }

    @Test
    public void test() {
        List<Member> fetch = jpaQueryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(2)
                .limit(2)
                .fetch();

        System.out.println(fetch);

        QueryResults<Member> members = jpaQueryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetchResults();

        assertThat(members.getTotal()).isEqualTo(4);
        assertThat(members.getLimit()).isEqualTo(2);
        assertThat(members.getOffset()).isEqualTo(1);
        assertThat(members.getResults().size()).isEqualTo(2);

    }
}
