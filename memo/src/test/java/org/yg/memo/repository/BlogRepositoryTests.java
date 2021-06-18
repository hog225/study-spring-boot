package org.yg.memo.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.yg.memo.dto.BlogDTO;
import org.yg.memo.dto.PageRequestDTO;
import org.yg.memo.dto.PageResultDTO;
import org.yg.memo.entity.Blog;
import org.yg.memo.entity.QBlog;
import org.yg.memo.service.BlogService;

import java.util.Optional;
import java.util.stream.IntStream;



@SpringBootTest
public class BlogRepositoryTests {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogService service;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1, 300).forEach(i -> {
            Blog blog = Blog.builder()
                    .title("title....." + i)
                    .content("content..." + i)
                    .writer("user" + (i%10))
                    .build();
            System.out.println(blogRepository.save(blog));
        });
    }

    @Test
    public void updateTest() {
        Optional<Blog> result = blogRepository.findById(300L);
        if (result.isPresent()){
            Blog blog = result.get();
            blog.changeTitle("change title.......");
            blog.changeContent("change Content.......");

            blogRepository.save(blog);
        }
    }

    @Test
    public void testQueryDsl1(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        QBlog qBlog = QBlog.blog;
        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = qBlog.title.contains(keyword); //where 에 조건을 넣어 주는 컨테이너
        builder.and(expression);
        Page<Blog> result = blogRepository.findAll(builder, pageable); // QuerydslPredicateExcutor의 findAll을 사용

        result.stream().forEach(blog -> {
            System.out.println(blog);
        });
    }

    @Test
    public void testRegister() {
        BlogDTO blogDTO = BlogDTO.builder()
                .title("sample title..")
                .content("sample content...")
                .writer("user0")
                .build();

        Long gno = service.register(blogDTO);
        Blog blog = service.getEntityByGno(gno);
        System.out.println( gno + "    --   "+ blog.getGno());
        Assertions.assertEquals(gno, blog.getGno());
    }

    @Test
    public void getList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<BlogDTO, Blog> resultDTO = service.getList(pageRequestDTO);

        for (BlogDTO blogDTO : resultDTO.getDtoList()){
            System.out.println(blogDTO);
        }
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<BlogDTO, Blog> resultDTO = service.getList(pageRequestDTO);

        System.out.println("prev" + resultDTO.isPrev());
        System.out.println("next" + resultDTO.isNext());
        System.out.println("total" + resultDTO.getTotalPage());


    }

}
