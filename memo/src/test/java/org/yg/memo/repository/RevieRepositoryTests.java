package org.yg.memo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yg.memo.entity.Member;
import org.yg.memo.entity.Movie;
import org.yg.memo.entity.MovieMember;
import org.yg.memo.entity.Review;

import javax.transaction.Transactional;
import java.util.List;
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

    // Review 의 Member 에 대한 Fetch 방식이 LAZY 이다. @Trasactional 을 이용하면
    // findByMovie 에서 Review에 대한 것만 쿼리하고 movieReview.getMember().getEmail() 가 호출될때 마다 쿼리를 계속 한다.
    // 이를 해결하기 위해서는 @Query를 이용해 Join 처리 하거나 @EntityGraph를 이용하여 Revie객체를 가져올때 Member 객체를 로딩하는 방법이 있다.
    @Test
    public void testGetMovieReviews(){
        Movie movie = Movie.builder()
                .mno(249L)
                .build();
        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(movieReview -> {
            System.out.println(movieReview.getReviewnum());
            System.out.println("\t" + movieReview.getMember().getEmail());

        });
    }
}
