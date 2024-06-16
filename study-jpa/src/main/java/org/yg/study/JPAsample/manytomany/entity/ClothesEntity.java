package org.yg.study.JPAsample.manytomany.entity;

import lombok.*;
import org.yg.study.JPAsample.manytomany.converter.GearTypeConverter;
import org.yg.study.JPAsample.manytomany.enums.GearType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "clothes")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClothesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clothesId;

    @Column(name = "maker", length = 50, nullable = false)
    private String maker;

    private Integer size;

    private String color;

    // 상의 하의 모자 신발 등등
    @Column(name = "type", length = 10, nullable = false)
    private String type;

    @OneToMany(mappedBy = "clothes", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private Set<GolferClothesEntity> clothes = new HashSet<>();


}
