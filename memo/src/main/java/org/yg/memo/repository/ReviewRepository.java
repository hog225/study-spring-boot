package org.yg.memo.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yg.memo.entity.Movie;
import org.yg.memo.entity.MovieMember;
import org.yg.memo.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 자동으로 Query에 Join 을 넣어 주며 Review의 Memeber를 한번에 가져올 수 있게 해준다.
    // 두개의 트렌젝션을 하나로 만들어 주는 역할을 한다. ..
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);

    //@EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    // @Query 가 없으면 member의 FK 인 Review 의 갯수만큼 삭제 Query 가 날라감으로 @Query 로 하는게 효율 적이다.
    @Modifying
    @Query("delete from Review mr where mr.member = :member")
    void deleteByMember(@Param("member") MovieMember member);



}
