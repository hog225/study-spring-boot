package org.yg.study.JPAsample.repository;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yg.study.JPAsample.dto.MemberSearchCondition;
import org.yg.study.JPAsample.dto.MemberTeamDto;
import org.yg.study.JPAsample.entity.Member;

// 사용자 정의 쿼리

public interface MemberRepositoryCustom{


    List<Member> findMemberCustom();

    //QueryDSL 로 구현 !!!!!!
    List<MemberTeamDto> search(MemberSearchCondition condition);
    Page<MemberTeamDto> searchSimple(MemberSearchCondition condition, Pageable pageable);
    Page<MemberTeamDto> searchComplex(MemberSearchCondition condition, Pageable pageable);

    List<Member> searchMemberAll();
    
}