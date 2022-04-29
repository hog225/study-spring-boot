package com.yg.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookGetRequestWithHeader extends BookCategoryRequest{


    private String key;

    @NotNull
    private String bookName;


    @NotNull
    private String writer;

}
