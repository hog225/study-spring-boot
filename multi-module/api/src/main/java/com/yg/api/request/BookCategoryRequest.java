package com.yg.api.request;

import com.yg.api.code.BookCategory;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookCategoryRequest {

    @NotNull
    private BookCategory category;
}
