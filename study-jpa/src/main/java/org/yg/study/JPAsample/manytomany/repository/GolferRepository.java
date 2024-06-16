package org.yg.study.JPAsample.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.study.JPAsample.manytomany.entity.GolferEntity;

public interface GolferRepository extends JpaRepository<GolferEntity, Long>, GolferCustomRepository {


}
