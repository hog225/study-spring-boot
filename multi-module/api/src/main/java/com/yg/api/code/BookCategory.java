package com.yg.api.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookCategory {
    FICTION("fiction"),
    NONFICTION("nonfiction"),
    ESSAY("essay");

    private String value;
}
