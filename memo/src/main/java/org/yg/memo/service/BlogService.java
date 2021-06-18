package org.yg.memo.service;

import org.yg.memo.dto.BlogDTO;
import org.yg.memo.dto.PageRequestDTO;
import org.yg.memo.dto.PageResultDTO;
import org.yg.memo.entity.Blog;

public interface BlogService {
    Long register(BlogDTO dto);

    Blog getEntityByGno(Long gno);

    BlogDTO read(Long gno);

    PageResultDTO<BlogDTO, Blog> getList(PageRequestDTO requestDTO);

    void modify(BlogDTO dto);

    void remove(Long gno);

    // default 키워드는 interface에서도 구현 해야만 한다.
    default Blog dtoToEntity(BlogDTO dto){
        Blog entity = Blog.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }



    default BlogDTO entityToDto(Blog entity){
        BlogDTO dto = BlogDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }


}
