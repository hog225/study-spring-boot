package org.yg.memo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.yg.memo.dto.BlogDTO;
import org.yg.memo.dto.PageRequestDTO;
import org.yg.memo.dto.PageResultDTO;
import org.yg.memo.entity.Blog;
import org.yg.memo.repository.BlogRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor // 초기화 되지 않은 final 필드나 @NoneNull 인 필드에 대해 생성자를 생성해준다.
public class BlogServiceImpl implements BlogService{

    // Spring 4.x 부터는 단일생성자에 한에 Autowired 를 붙이지 않아도 DI가 가능하다.
    // 그럼으로 아래 코드는 RequiredArgsConstructor 에의해 단일 생성자가 생성되었고 생성자 Injection이 된다.
    // 그리고 Feild injection은 추천되지 않는 방법이다. 이유는 https://studiou.tistory.com/9 참조한다.
    private final BlogRepository repo;
    // 만약 RequiredArgsConstructor 안쓰면 아래처럼 해서 생성자 주입 방식을 할 수 있다.
//    @Autowired
//    public BlogServiceImpl(BlogRepository repo){
//        this.repo = repo;
//    }


    @Override
    public Long register(BlogDTO dto) {
        log.info("DTO--------------");
        Blog entity = dtoToEntity(dto);

        log.info(entity.toString());
        repo.save(entity);
        return entity.getGno();

    }

    @Override
    public Blog getEntityByGno(Long gno) {
        return repo.getOne(gno);
    }

    @Override
    public BlogDTO read(Long gno) {
        Optional<Blog> result = repo.findById(gno);
        return result.isPresent()?entityToDto(result.get()): null;
    }

    @Override
    public PageResultDTO<BlogDTO, Blog> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
        Page<Blog> result = repo.findAll(pageable);

        return new PageResultDTO<>(result, entity->entityToDto(entity));

    }

    @Override
    public void modify(BlogDTO dto) {
        Optional<Blog> result = repo.findById(dto.getGno());
        if(result.isPresent()){
            Blog entity = result.get();
            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());
            repo.save(entity);
        }
    }

    @Override
    public void remove(Long gno) {
        repo.deleteById(gno);
    }


}
