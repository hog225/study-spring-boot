package com.yg.external.publicapis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class EntryResponse {
    private String count;
    private List<Entry> entries;

    @Getter
    @Setter
    @ToString
    public static class Entry {
        @JsonProperty("API")
        private String API;
        @JsonProperty("Description")
        private String Description;
        @JsonProperty("Auth")
        private String Auth;
        @JsonProperty("HTTPS")
        private String HTTPS;
        @JsonProperty("Cors")
        private String Cors;
        @JsonProperty("Link")
        private String Link;
        @JsonProperty("Category")
        private String Category;
    }
}
