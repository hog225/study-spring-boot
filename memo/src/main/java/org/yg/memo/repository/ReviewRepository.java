package org.yg.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.memo.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
