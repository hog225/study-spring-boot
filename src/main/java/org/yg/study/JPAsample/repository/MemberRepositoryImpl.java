package org.yg.study.JPAsample.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.yg.study.JPAsample.entity.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final EntityManager em;

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m")
            .getResultList();
    }
    
}
