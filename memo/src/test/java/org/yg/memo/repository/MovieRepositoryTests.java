package org.yg.memo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.yg.memo.entity.Movie;
import org.yg.memo.entity.MovieImage;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository imageRepository;

    @Commit
    @Transactional
    @Test
    public void insertMovies() {
        IntStream.rangeClosed(1, 100).forEach(i-> {
            Movie movie = Movie.builder().title("MOVIE .........." + i).build();
            System.out.println("--------");
            movieRepository.save(movie);

            int count = (int)(Math.random() * 5) + 1;

            for (int j=0; j<count; j++){
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test" + j + ".jpg")
                        .build();
                imageRepository.save(movieImage);
            }
            System.out.println("--------");
        });
    }

    @Test
    public void testListPage(){
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));

        Page<Object[]> mPage =  movieRepository.getListPage(pageRequest);

        for (Object[] objects: mPage){
            System.out.println(Arrays.toString(objects));
        }
    }
}
