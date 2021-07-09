package org.yg.memo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yg.memo.entity.Movie;



public interface MovieRepository extends JpaRepository<Movie, Long> {

    // coalesce == ifnull
    // distinct == 중복제거
    // 아래처럼 주석 처럼 하면 N+1 문제가 발생하여 movieImage를 10번 조회 하게 된다.
//    @Query("select m, max(mi), avg(coalesce(r.grade,0)), count(distinct r) from Movie m " +
//            "left outer join MovieImage mi on mi.movie = m " +
//            "left outer join Review r on r.movie = m group by m")
    @Query("select m, mi, avg(coalesce(r.grade,0)), count(distinct r) from Movie m " +
            "left outer join MovieImage mi on mi.movie = m " +
            "left outer join Review r on r.movie = m group by m")
    Page<Object[]> getListPage(Pageable pageable);
}
