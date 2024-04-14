package org.yg.study.JPAsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.yg.study.JPAsample.entity.Team;

import java.util.List;

//
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select t from Team t")
    List<Team> findTeams();
}
