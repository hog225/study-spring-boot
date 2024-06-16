package org.yg.study.JPAsample.manytomany.entity;


import lombok.*;
import org.yg.study.JPAsample.manytomany.converter.GradeConverter;
import org.yg.study.JPAsample.manytomany.enums.Grade;

import javax.persistence.*;

@Table(name = "golfer_gear")
@Entity
@Getter
@Setter
@Builder
@IdClass(GolferGearEntityId.class)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GolferGearEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "golfer_seq")
    private GolferEntity golfer;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gear_id")
    private GearEntity gear;


}
