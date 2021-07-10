package org.yg.memo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.yg.memo.entity.MovieMember;

import javax.transaction.Transactional;
import java.util.stream.IntStream;

@SpringBootTest
public class MovieMemberRepositoryTests {

    @Autowired
    private MovieMemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

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

    @Transactional
    @Commit
    @Test
    public void deleteMembers(){
        Long mid = 99L;

        MovieMember member = MovieMember.builder().mid(mid).build();

        // 이렇게 하면 PK 를 먼저 삭제 하기 때문에 에러가 발생한다.
        //memberRepository.deleteById(mid);
        //reviewRepository.deleteByMember(member);

        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);

    }
}
