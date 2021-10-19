package org.yg.study.JPAsample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.querydsl.core.NonUniqueResultException;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.yg.study.JPAsample.entity.QMember.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.yg.study.JPAsample.entity.QTeam.*;

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

    @Test
    public void aggregation() {
        List<Tuple> fetch = jpaQueryFactory
                .select(
                        member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.min()
                )
                .from(member)
                .fetch();

        Tuple tuple = fetch.get(0);
        System.out.println(tuple.get(member.count()));
        System.out.println(tuple.get(member.age.sum()));

    }

    /**
     * 팀의 이름과 각 팀의 평균 연령
     * @throws Exception
     */
    @Test
    public void groupBy() throws Exception {
        List<Tuple> result = jpaQueryFactory
                .select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();

        result.stream().forEach(tup -> {
            System.out.println(tup.get(team.name));
            System.out.println(tup.get(member.age.avg()));
        });

    }

    @Test
    public void join(){
        List<Member> teamA = jpaQueryFactory
                .selectFrom(member)
                .leftJoin(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();

        assertThat(teamA)
                .extracting("username")
                .containsExactly("member1", "member2");
    }
    // 연관관계가 없어도 join 이 된다.
    @Test
    public void theta_join() {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));

        List<Member> result = jpaQueryFactory
                .select(member)
                .from(member, team)
                .where(member.username.eq(team.name))
                .fetch();

        System.out.println(result);
    }

    /**
     * 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회
     */
    @Test
    public void join_on_filtering() {
        List<Tuple> teamA = jpaQueryFactory
                .select(member, team)
                .from(member)
                .leftJoin(member.team, team).on(team.name.eq("teamA"))
                .fetch();


        teamA.stream().forEach(tup -> {
            System.out.println("tup" + tup);
        });

    }

    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    public void fetchJoin(){
        em.flush();
        em.clear();

        Member findMember = jpaQueryFactory
                .selectFrom(member)
                .join(member.team, team).fetchJoin()
                .where(member.username.eq("member1"))
                .fetchOne();

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        assertThat(loaded).as("패치조인 미적용 ").isTrue();

    }

    //Subquery

    /**
     * 나이가 가장 많은 회원
     */
    @Test
    public void subQuery() {

        QMember mSub = new QMember("sub");
        Member oldestMember = jpaQueryFactory
                .selectFrom(member)
                .where(member.age.eq(
                        JPAExpressions.select(mSub.age.max()).from(mSub)
                ))
                .fetchOne();

        assertThat(oldestMember).extracting("age").isEqualTo(40);

    }

    @Test
    public void subQueryGoe() {

        QMember mSub = new QMember("sub");
        List<Member> goeMember = jpaQueryFactory
                .selectFrom(member)
                .where(member.age.goe(
                        JPAExpressions.select(mSub.age.avg()).from(mSub)
                ))
                .fetch();

        assertThat(goeMember).extracting("age").containsExactly(30, 40);


    }

    @Test
    public void subQueryIn() {

        QMember mSub = new QMember("sub");
        List<Member> goeMember = jpaQueryFactory
                .selectFrom(member)
                .where(member.age.in(
                        JPAExpressions
                                .select(mSub.age)
                                .from(mSub)
                                .where(mSub.age.gt(20))
                ))
                .fetch();

        assertThat(goeMember).extracting("age").containsExactly(30, 40);


    }


    @Test
    public void subQueryOnSelect() {

        QMember mSub = new QMember("sub");
        List<Tuple> goeMember = jpaQueryFactory
                .select(member, JPAExpressions.select(mSub.age.avg()).from(mSub))
                .from(member)
                .fetch();

        goeMember.stream().forEach(tup -> {
            System.out.println("tup " + tup.get(0, Member.class) + tup.get(1, Integer.class));
            assertThat(tup.get(0, Member.class)).extracting("username").isNotNull();

        });




    }

    @Test
    public void testCase() {
        List<String> result = jpaQueryFactory
                .select(member.age
                        .when(10).then("십살")
                        .when(20).then("이십살")
                        .otherwise("나이많음"))
                .from(member)
                .fetch();



        assertThat(result).containsAll(Arrays.asList("십살", "이십살", "나이많음"));

    }

    @Test
    public void numberRank() {
        NumberExpression<Integer> rankPath = new CaseBuilder()
                .when(member.age.between(0, 20)).then(2)
                .when(member.age.between(21, 30)).then(1)
                .otherwise(3);
        List<Tuple> result = jpaQueryFactory
                .select(member.username, member.age, rankPath)
                .from(member)
                .orderBy(rankPath.desc())
                .fetch();
        for (Tuple tuple : result) {
            String username = tuple.get(member.username);
            Integer age = tuple.get(member.age);
            Integer rank = tuple.get(rankPath);
            System.out.println("username = " + username + " age = " + age + " rank = "
                    + rank);
        }
    }

    @Test
    public void constant() {
        List<Tuple> result = jpaQueryFactory
                .select(member.username, Expressions.constant("A"))
                .from(member)
                .fetch();

        jpaQueryFactory
                .select(member.username.concat("_").concat(member.age.stringValue()))
                .from(member)
                .where(member.username.eq("member1"))
                .fetch();
    }

}
