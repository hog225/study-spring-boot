package com.yg.external.publicapis.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PublicUri {

    ENTRIES("/entries");

    private String value;
}
