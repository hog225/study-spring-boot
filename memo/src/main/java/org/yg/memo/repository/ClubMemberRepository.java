package org.yg.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.memo.entity.ClubMember;

public interface ClubMemberRepository extends JpaRepository<ClubMember, String> {
}
