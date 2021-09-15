package org.yg.memo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yg.memo.entity.BiMovie;

public interface BiMovieRepository extends JpaRepository<BiMovie, Long> {

    @EntityGraph(attributePaths = "posterList", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from BiMovie m")
    Page<BiMovie> findAll2(Pageable pageable);
}
