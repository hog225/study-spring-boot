package org.yg.study.JPAsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yg.study.JPAsample.entity.Team;

//
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
