package com.yg.domain.book.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String bookName;

    String author;

    ZonedDateTime released;

    public static Book makeBook(String bookName, String author, ZonedDateTime released) {
        return Book.builder()
                .bookName(bookName)
                .author(author)
                .released(released)
                .build();
    }


}
