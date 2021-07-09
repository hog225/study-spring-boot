package org.yg.memo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yg.memo.entity.MovieMember;

import java.util.stream.IntStream;

@SpringBootTest
public class MovieMemberRepositoryTests {

    @Autowired
    private MovieMemberRepository memberRepository;

    @Test
    public void insertmembers() {
        IntStream.rangeClosed(1, 100).forEach(i->{
            MovieMember member = MovieMember.builder()
                    .email("r"+i+"@gmail.com")
                    .pw("1111")
                    .nickname("review" + i).build();
            memberRepository.save(member);
        });
    }
}
