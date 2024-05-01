package org.yg.study.JPAsample.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.ast.SqlScriptParserException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.entity.Book;
import org.yg.study.JPAsample.repository.BookRepository;

import java.time.ZonedDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class BookSilentRollbackService {
    private final BookSave bookSave;

    public void insert() {
        try {
            bookSave.save();
        } catch (Exception e) {
            System.out.println("Exception");
        }

    }



}
