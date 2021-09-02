package org.yg.study.JPAsample.repository;

import java.util.List;

import org.yg.study.JPAsample.entity.Member;

// 사용자 정의 쿼리 
public interface MemberRepositoryCustom{
    List<Member> findMemberCustom();
    
}