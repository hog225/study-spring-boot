package org.yg.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.memo.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long>{
  
}