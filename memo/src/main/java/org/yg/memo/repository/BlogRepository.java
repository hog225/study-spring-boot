package org.yg.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.yg.memo.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long>, QuerydslPredicateExecutor<Blog> {

}
