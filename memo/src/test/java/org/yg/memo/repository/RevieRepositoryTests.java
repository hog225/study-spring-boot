package org.yg.memo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yg.memo.entity.Member;
import org.yg.memo.entity.Movie;
import org.yg.memo.entity.MovieMember;
import org.yg.memo.entity.Review;

import java.util.stream.IntStream;

@SpringBootTest
public class RevieRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMovieReviews() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            // movie number
            Long mno = (long)(Math.random()*100) + 200;

            // Reviewer number
            Long mid = (long)(Math.random()*100) + 1;
            MovieMember member = MovieMember.builder().mid(mid).build();

            Review movieReview = Review.builder()
                    .member(member)
                    .movie(Movie.builder().mno(mno).build())
                    .grade((int)(Math.random()* 5) + 1)
                    .text("영화 느낌 ?? .." + i)
                    .build();
            reviewRepository.save(movieReview);
        });
    }
}
