package com.yg.api.request;

import com.yg.api.validated.ValidationGroups;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class BookGetRequestValidated extends BookCategoryRequest{


    @NotEmpty(groups = {ValidationGroups.BookName.class})
    private String bookName;


    @NotEmpty(groups = {ValidationGroups.BookWriter.class})
    private String writer;

}
