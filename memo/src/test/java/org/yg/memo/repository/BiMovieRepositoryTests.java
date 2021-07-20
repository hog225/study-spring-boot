package org.yg.memo.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.yg.memo.entity.BiMovie;
import org.yg.memo.entity.BiPoster;

import javax.transaction.Transactional;

@SpringBootTest

public class BiMovieRepositoryTests {

    @Autowired
    private BiMovieRepository movieRepository;

    // 아래와 같이 하면 BiMovie 객체만 DB에 저장된다.
    // cascade를 지정하지 않으면 BiMovie의 하위 객체에게도 저장이 전파 되어야 하는데 이때 cascade를 사용한다.(BiMovie 객체 에서)
    @Test
    public void testInsert(){
        System.out.println("test insert ");

        BiMovie movie = BiMovie.builder()
                .title("극한직업")
                .build();

        movie.addPoster(BiPoster.builder().fname("극직포스터1.jpg").build());
        movie.addPoster(BiPoster.builder().fname("극직포스터2.jpg").build());

        movieRepository.save(movie);
        System.out.println(movie.getMno());
    }

    @Test
    @Transactional
    @Commit
    public void testAddPosterOne(){
        BiMovie movie = movieRepository.getOne(1L); // DB 에 존재한는 영화 번호
        movie.addPoster(BiPoster.builder().fname("극직포스터3.jpg").build());
        movieRepository.save(movie);
    }

    @Test
    @Transactional
    @Commit
    public void testRemovePoster(){
        BiMovie movie = movieRepository.getOne(1L); // DB 에 존재한는 영화 번호
        movie.removePoster(0L);
        movieRepository.save(movie);
    }
}
