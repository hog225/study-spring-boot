package org.yg.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yg.memo.entity.Board;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {
    // 연관관계가 있는 JOIN의 경우에는 ON을 쓸필요가 없다.
    @Query("select b, w from Board b left join b.writer w where b.bno =:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    @Query("select b, r from Board b LEFT JOIN Reply r ON r.board = b where b.bno =: bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);
}
