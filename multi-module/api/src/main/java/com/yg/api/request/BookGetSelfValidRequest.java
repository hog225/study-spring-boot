package com.yg.api.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class BookGetSelfValidRequest extends BookCategorySelfValidRequest {

    @NotNull
    private String bookName;


    @NotNull
    private String writer;
}
