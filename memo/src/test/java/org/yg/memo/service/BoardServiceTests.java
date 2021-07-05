package org.yg.memo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yg.memo.dto.BoardDTO;
import org.yg.memo.dto.PageRequestDTO;
import org.yg.memo.dto.PageResultDTO;

@SpringBootTest
public class BoardServiceTests {
    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {
        BoardDTO dto = BoardDTO.builder()
                .title("test.")
                .content("Test...")
                .writerEmail("power16@gmail.com")
                .build();
        Long bno = boardService.register(dto);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);

        for (BoardDTO boardDTO: result.getDtoList()){
            System.out.println(boardDTO);
        }
    }

    @Test
    public void removeTest() {
        Long bno = 7L;

        BoardDTO boardDTO = boardService.get(bno);
        System.out.println(boardDTO.toString());

        boardService.removeWithReplies(bno);
    }

    @Test
    public void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(2L)
                .title("제목ㄹㄷㄹㄷㄹ")
                .content("내뇽ㅇㄹㄷ")
                .build();
        boardService.modify(boardDTO);


    }
}
