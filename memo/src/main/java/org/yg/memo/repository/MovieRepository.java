package org.yg.memo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yg.memo.entity.Movie;

import java.util.List;


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

    // 가장 나중에 추가된 이미지로 쿼리 할 시
    @Query("select m, i, count(r) from Movie m left join MovieImage i on i.movie = m " +
            "and i.inum = (select max(i2.inum) from MovieImage i2 where i2.movie = m) " +
            "left outer join Review r on r.movie = m group by m")
    Page<Object[]> getListPageLastImage(Pageable pageable);

    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(r) from Movie m left outer join MovieImage mi on mi.movie = m " +
            "left outer join Review r on r.movie = m " +
            "where m.mno = :mno group by mi")
    List<Object[]> getMovieWithAll(@Param("mno") Long mno);


}
