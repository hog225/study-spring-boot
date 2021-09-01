package org.yg.study.JPAsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yg.study.JPAsample.dto.MemberDto;
import org.yg.study.JPAsample.entity.Member;

import java.util.Collection;
import java.util.List;

// Spring JPA 사용
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findByUsernameIgnoreCase(String username);

    @Query(value = "select m from Member m where m.username = :username")
    List<Member> findByUsername(@Param("username") String username);

    @Query("select new org.yg.study.JPAsample.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m.id, m.username, t.name from Member m join m.team t")
    List<Object[]> findMemberDto2();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);
}
