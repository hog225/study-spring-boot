package org.yg.practivce.springbatch.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yg.practivce.springbatch.domain.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
