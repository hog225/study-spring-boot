package org.yg.study.JPAsample.manytomany.entity;


import lombok.*;
import org.yg.study.JPAsample.manytomany.converter.GradeConverter;
import org.yg.study.JPAsample.manytomany.enums.Grade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "golfer")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GolferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long golferSeq;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "age")
    private Integer age;

    @Column(name = "grade", length = 10, nullable = false)
    @Convert(converter = GradeConverter.class)
    @Builder.Default
    private Grade grade = Grade.AMATEUR;

    @OneToMany(mappedBy = "golfer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private Set<GolferGearEntity> gears = new HashSet<>();

    @OneToMany(mappedBy = "golfer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private Set<GolferClothesEntity> clothes = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_seq")
    private FieldEntity field;

    public void addGolferGears(Set<GolferGearEntity> entities) {
        this.gears.addAll(entities);
    }

    public void addGolferClothes(Set<GolferClothesEntity> entities) {
        this.clothes.addAll(entities);
    }



}
