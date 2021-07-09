package org.yg.memo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yg.memo.entity.Board;
import org.yg.memo.entity.Reply;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyRepositoryTests {

    @Autowired
    ReplyRepository replyRepository;

    @Test
    void insertReplys(){
        IntStream.rangeClosed(1, 300).forEach(idx->{
            long bno = (long)(Math.random() * 100) + 1;
            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .replyer("replyer" + idx)
                    .text("You are ... mo..." + idx)
                    .board(board)
                    .build();

            replyRepository.save(reply);

        });

    }

    @Test
    void testRead1(){
        // Left Outer Join 사용
        // Left 는 Board
        // member, Borad, Reply 싹들고 옴
        Optional<Reply> results = replyRepository.findById(100L);

        Reply reply = results.get();
        System.out.println(reply); //board
        System.out.println(reply.getBoard()); // member

    }

    @Test
    void readRepliesByBoard(){

        Board board = Board.builder().bno(97L).build();

        List<Reply> replyList = replyRepository.getRepliesByBoardOrderByRno(board);
        replyList.forEach(reply -> System.out.println(reply));
    }

}