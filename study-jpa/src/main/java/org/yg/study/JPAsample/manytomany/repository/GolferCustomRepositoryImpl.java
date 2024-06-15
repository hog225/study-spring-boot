package org.yg.study.JPAsample.manytomany.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.yg.study.JPAsample.manytomany.entity.GolferEntity;

@Repository
public class GolferCustomRepositoryImpl extends QuerydslRepositorySupport implements GolferCustomRepository {

    private final JPAQueryFactory queryFactory;
    public GolferCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        super(GolferEntity.class);
        this.queryFactory = queryFactory;
    }
}
