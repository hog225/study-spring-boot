package org.yg.study.JPAsample.entity;

import lombok.*;
import org.yg.study.JPAsample.converter.TruncateStringConverter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(length = 10)
    @Convert(converter = TruncateStringConverter.class)
    String name;

    @Column()
    ZonedDateTime publictionDate;

    public static Book create(String name, ZonedDateTime publictionDate) {
        return Book.builder().name(name).publictionDate(publictionDate).build();
    }


}
