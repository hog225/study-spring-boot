package org.yg.memo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yg.memo.entity.Board;
import org.yg.memo.entity.Member;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void insertBoards(){
        IntStream.rangeClosed(1, 100).forEach(idx -> {
            Member member = Member.builder().email( "power"+ idx +"@gmail.com").build();
            Board board = Board.builder()
                    .content("Content ____? " + idx)
                    .title("title ______"+ idx)
                    .writer(member)
                    .build();
            boardRepository.save(board);
        });
    }
    @Transactional
    @Test
    void testRead1(){
        //
        // FETCH TYPE EAGER(Default) 의 경우 LEFT JOIN을 사용하여 results 에 Member, Board 모두 로드 된다.
        // LAZY 의 경우엔 우선 Board 만 조회 한다. 그리고 Transactional 애노테이션(속성에 따라)의 도옴을 통해 getWriter가 호출 될 때 Member를 조회한다.
        Optional<Board> results = boardRepository.findById(100L);

        Board bord = results.get();
        System.out.println(bord); //board
        System.out.println(bord.getWriter()); // member

    }

    @Test
    public void testReadWithWriter() {
        Object result = boardRepository.getBoardWithWriter(100L);
        Object[] arr = (Object[]) result;
        System.out.println("-----------------");
        System.out.println(Arrays.toString(arr));
    }

}