package org.yg.memo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yg.memo.dto.BlogDTO;
import org.yg.memo.dto.PageRequestDTO;
import org.yg.memo.dto.PageResultDTO;
import org.yg.memo.entity.Blog;

@SpringBootTest
public class BlogServiceTests {

    @Autowired
    private BlogService service;

    // 실제 데이터베이스에 저장되지는 않지만 GuestbookDTO를 Guestbook 엔티티로 변환한 결과 확인 가능
    @Test
    public void testRegister() {
        BlogDTO guestbookDTO = BlogDTO.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("user0")
                .build();

        System.out.println(service.register(guestbookDTO));
    }

//    @Test
//    public void testList() {
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
//        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);
//
//        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
//            System.out.println(guestbookDTO);
//        }
//    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<BlogDTO, Blog> resultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV: "+resultDTO.isPrev());
        System.out.println("NEXT: "+resultDTO.isNext());
        System.out.println("TOTAL: " + resultDTO.getTotalPage());

        System.out.println("-------------------------------------");
        for (BlogDTO blogDTO : resultDTO.getDtoList()) {
            System.out.println(blogDTO);
        }

        System.out.println("========================================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }

    @Test
    public void testSearch() {
        // 제목이나 내용에 '한글' 키워드가 있는 글을 검색
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("tc") // 검색 조건
                .keyword("한글") // 검색 키워드
                .build();

        PageResultDTO<BlogDTO, Blog> resultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV: " + resultDTO.isPrev());
        System.out.println("NEXT: " + resultDTO.isNext());
        System.out.println("TOTAL: " + resultDTO.getTotalPage());

        System.out.println("--------------------");
        for (BlogDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println(guestbookDTO);
        }

        System.out.println("====================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }
}
