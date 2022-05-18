package org.yg.study.JPAsample.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long seq;

    @Column
    private String name;

    public static Writer create(String name) {
        return Writer.builder()
                .name(name)
                .build();
    }
}
