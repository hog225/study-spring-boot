package org.yg.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.memo.entity.MovieMember;

public interface MovieMemberRepository extends JpaRepository<MovieMember, Long> {
}
