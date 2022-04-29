package com.yg.api.request;

import com.yg.api.code.BookCategory;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class BookGetRequest extends BookCategoryRequest{


    @NotNull
    private String bookName;


    @NotNull
    private String writer;

}
