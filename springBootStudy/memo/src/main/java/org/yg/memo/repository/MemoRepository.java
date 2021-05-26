package org.yg.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yg.memo.entity.Memo;
import java.util.*;

import javax.transaction.Transactional;

import org.springframework.data.domain.*;

public interface MemoRepository extends JpaRepository<Memo, Long>{
  List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

  Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);

  // 특정 값보다 작은 데이터 삭제 
  void deleteMemoByMnoLessThan(Long num);

  //일반적인 간단한 경우에만 Query Metho를 이용하고 그게 아닌 경우엔 @Query 애노테이션을 사용한다. 
  @Transactional
  @Modifying
  @Query("update Memo m set m.memoText = :memoText where m.mno= :mno")
  int updateMemoText(@Param("mno") Long mno, @Param("memoText") String memoText);

  @Transactional
  @Modifying
  @Query("update Memo m set m.memoText = :#{#param.memoText} where m.mno= :#{#param.mno}")
  int updateMemoText(@Param("param") Memo memo);

  @Query(value="select m from Memo m where m.mno < :mno", countQuery= "select count(m) from Memo m where m.mno < :mno")
  Page <Memo> getListWithQuery(@Param("mno") Long mno, Pageable pageable);

  @Query(value = "select m.mno, m.memoText, CURRENT_DATE from Memo m where m.mno > :mno", 
        countQuery = "select count(m) from Memo m where m.mno > :mno")
  Page<Object[]>getListWithQueryObject(@Param("mno") Long mno, Pageable pageable);

  //NativeSQL
  @Query(value= "select * from tbl_memo where mno > 0", nativeQuery = true)
  List<Object[]> getNativeResult();
}