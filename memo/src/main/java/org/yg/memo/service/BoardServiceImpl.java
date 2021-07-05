package org.yg.memo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.yg.memo.dto.BoardDTO;
import org.yg.memo.dto.PageRequestDTO;
import org.yg.memo.dto.PageResultDTO;
import org.yg.memo.entity.Board;
import org.yg.memo.entity.Member;
import org.yg.memo.repository.BoardRepository;
import org.yg.memo.repository.ReplyRepository;

import javax.transaction.Transactional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{
    private final BoardRepository repository;
    private final ReplyRepository replyRepository;

    @Override
    public Long register(BoardDTO dto) {
        log.info(dto);
        Board board = dtoToEntity(dto);
        repository.save(board);
        return board.getBno();

    }
    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO){
        Function<Object[], BoardDTO> fn = (en -> entityToDto((Board)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> result = repository.getBoardWithReplyCount(
                pageRequestDTO.getPageable(Sort.by("bno").descending()));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Long bno) {
        Object result = repository.getBoardByBno(bno);
        Object[] arr = (Object[]) result;
        return entityToDto((Board)arr[0], (Member)arr[1], (Long)arr[2]);

    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno) {
        replyRepository.deleteByBno(bno);
        repository.deleteById(bno);

    }

    @Transactional
    @Override
    public void modify(BoardDTO boardDTO){
        // getOne 의 경우엔 Proxy를 가져 옴으로 Transactional이 필요 하다.
        Board board = repository.getOne(boardDTO.getBno());
        if (board != null){
            board.changeTitle(boardDTO.getTitle());
            board.changCount(boardDTO.getContent());
            repository.save(board);
        }
    }

}
