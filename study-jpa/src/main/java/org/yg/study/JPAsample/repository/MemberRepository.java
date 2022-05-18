package org.yg.study.JPAsample.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.yg.study.JPAsample.dto.MemberDto;
import org.yg.study.JPAsample.entity.Member;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Collection;
import java.util.List;


// Spring JPA 사용
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom, QuerydslPredicateExecutor<Member> {

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

    // count 쿼리는 고비용 일 수 있다. 그럴때 아래와 같이 Count 쿼리를 분리 할 수 있다. 
    @Query(
        value = "select m from Member m left join m.team t",
        countQuery = "select count(m.username) from Member m"
    )
    Page<Member> findByAge(int age, Pageable pageable);

    @Modifying(clearAutomatically = true) //JAP excuteUpdate()
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePluse(@Param("age") int age);

    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();
    
    
    @EntityGraph(attributePaths = {"team"})
    List<Member> findEntityGraphByUsername(@Param("username") String username);

    // 
    @QueryHints(value = @QueryHint(name = "org.spring.readOnly", value = "true"))
    Member findReadOnlyByUsername(String username);

    // @Lock(LockModeType.PESSIMISTIC_WRITE)
    // List<Member> findLockByUserName(String username);
}
