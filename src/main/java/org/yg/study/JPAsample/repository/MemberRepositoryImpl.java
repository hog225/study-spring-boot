package org.yg.study.JPAsample.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.yg.study.JPAsample.dto.MemberSearchCondition;
import org.yg.study.JPAsample.dto.MemberTeamDto;
import org.yg.study.JPAsample.dto.QMemberTeamDto;
import org.yg.study.JPAsample.entity.Member;
import static org.yg.study.JPAsample.entity.QMember.*;
import static org.yg.study.JPAsample.entity.QTeam.team;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m")
            .getResultList();
    }

    @Override
    public List<MemberTeamDto> search(MemberSearchCondition condition){
        return queryFactory
                .select(new QMemberTeamDto(
                        member.id,
                        member.username,
                        member.age,
                        team.id,
                        team.name
                ))
                .from(member)
                .leftJoin(member.team, team)
                .where(
                        usernameEq(condition.getUsername())
                        ,teamNameEq(condition.getTeamName())
                        ,ageGoe(condition.getAgeGoe())
                        ,ageLoe(condition.getAgeLoe())
                )
                .fetch();


    }

    @Override
    public Page<MemberTeamDto> searchSimple(MemberSearchCondition condition, Pageable pageable) {
        QueryResults<MemberTeamDto> results = queryFactory
                .select(new QMemberTeamDto(
                        member.id,
                        member.username,
                        member.age,
                        team.id,
                        team.name
                ))
                .from(member)
                .leftJoin(member.team, team)
                .where(
                        usernameEq(condition.getUsername())
                        , teamNameEq(condition.getTeamName())
                        , ageGoe(condition.getAgeGoe())
                        , ageLoe(condition.getAgeLoe())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                // Contents query + count query
                .fetchResults();
        List<MemberTeamDto> content = results.getResults();
        long total =results.getTotal();

        return new PageImpl<>(content, pageable, total);

    }

    @Override
    public Page<MemberTeamDto> searchComplex(MemberSearchCondition condition, Pageable pageable) {
        List<MemberTeamDto> content = queryFactory
                .select(new QMemberTeamDto(
                        member.id,
                        member.username,
                        member.age,
                        team.id,
                        team.name
                ))
                .from(member)
                .leftJoin(member.team, team)
                .where(
                        usernameEq(condition.getUsername())
                        , teamNameEq(condition.getTeamName())
                        , ageGoe(condition.getAgeGoe())
                        , ageLoe(condition.getAgeLoe())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                // Contents query + count query
                .fetch();

        // count query 가 매우 쉽게 나올수 있을때 사용 한다.
//        long total = queryFactory
//                .select(member)
//                .from(member)
//                .leftJoin(member.team, team)
//                .where(
//                        usernameEq(condition.getUsername())
//                        , teamNameEq(condition.getTeamName())
//                        , ageGoe(condition.getAgeGoe())
//                        , ageLoe(condition.getAgeLoe())
//                )
//                .fetchCount();
        //return new PageImpl<>(content, pageable, total);
        JPAQuery<Member> countQuery = queryFactory
                .select(member)
                .from(member)
                .leftJoin(member.team, team)
                .where(
                        usernameEq(condition.getUsername())
                        , teamNameEq(condition.getTeamName())
                        , ageGoe(condition.getAgeGoe())
                        , ageLoe(condition.getAgeLoe())
                );

        //Count Query 가 필요할때만 날림
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);

    }

    private BooleanExpression usernameEq(String username){
        return username != null ? member.username.eq(username):null;
    }
    private BooleanExpression teamNameEq(String teamName) {
        return teamName == null ? null : team.name.eq(teamName);
    }
    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe == null ? null : member.age.goe(ageGoe);
    }
    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe == null ? null : member.age.loe(ageLoe);
    }




    @Override
    public List<Member> searchMemberAll() {
        List<Member> members = queryFactory
                .selectFrom(member).fetch();
        return members;

    }

}
