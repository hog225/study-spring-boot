package org.yg.practivce.springbatch.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.text.html.parser.DTD;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;


}
