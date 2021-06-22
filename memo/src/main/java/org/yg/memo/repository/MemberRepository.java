package org.yg.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.memo.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
}
