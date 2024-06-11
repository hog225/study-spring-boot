package org.yg.study.JPAsample.manytomany.entity;


import lombok.*;

import javax.persistence.*;

@Table(name = "golfer_clothes")
@Entity
@Getter
@Setter
@Builder
@IdClass(GolferGearEntityId.class)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GolferClothesEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "golfer_seq")
    private GolferEntity golfer;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clothes_seq")
    private ClothesEntity clothes;


}
