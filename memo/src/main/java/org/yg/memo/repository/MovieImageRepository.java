package org.yg.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.memo.entity.MovieImage;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}
