package org.yg.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.memo.entity.BiMovie;

public interface BiMovieRepository extends JpaRepository<BiMovie, Long> {
}
