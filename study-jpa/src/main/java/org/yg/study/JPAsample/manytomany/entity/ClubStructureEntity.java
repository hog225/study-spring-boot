package org.yg.study.JPAsample.manytomany.entity;

import lombok.*;
import org.yg.study.JPAsample.manytomany.enums.ClubStructureType;

import javax.persistence.*;

@Table(name = "club_structure")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClubStructureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubStructureSeq;


    @Column(name = "type", length = 50, nullable = false)
    @Convert(converter = ClubStructureType.class)
    private ClubStructureType type;

    private String manufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gear_seq")
    private GearEntity club;



}
