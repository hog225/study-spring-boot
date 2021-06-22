package org.yg.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.memo.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
