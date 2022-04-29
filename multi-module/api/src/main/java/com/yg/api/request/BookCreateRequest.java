package com.yg.api.request;

import com.yg.api.code.BookCategory;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookCreateRequest {
    @NotNull
    private String bookName;
    @NotNull
    private String writer;

    @NotNull
    private BookCategory category;

}
