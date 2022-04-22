package com.yg.domain.book.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
@Data
public class Book {

    @Id
    Long id;

    String bookName;

    String author;

    ZonedDateTime released;


}
