package yg.study.studyspringbatch.domain.book;

import lombok.*;

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
    String name;

    @Column()
    ZonedDateTime publictionDate;

    public static Book create(String name, ZonedDateTime publictionDate) {
        return Book.builder().name(name).publictionDate(publictionDate).build();
    }


}
