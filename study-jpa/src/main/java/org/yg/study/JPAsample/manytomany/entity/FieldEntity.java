package org.yg.study.JPAsample.manytomany.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Table(name = "field")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FieldEntity {
    @Id
    private Long fieldSeq;

    private String name;

    private String address;

    @OneToMany(mappedBy = "field")
    private List<GolferEntity> golfers;
}
