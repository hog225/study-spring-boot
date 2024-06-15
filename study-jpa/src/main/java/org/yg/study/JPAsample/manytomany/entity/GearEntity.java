package org.yg.study.JPAsample.manytomany.entity;

import lombok.*;
import org.yg.study.JPAsample.manytomany.converter.GearTypeConverter;
import org.yg.study.JPAsample.manytomany.enums.GearType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "gear")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GearEntity {
    @Id
    @Column(name = "gear_id")
    private String gearId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "gear_type", length = 10, nullable = false)
    @Convert(converter = GearTypeConverter.class)
    private GearType gearType;

    @Column(name = "club_number")
    private Integer clubNumber;

    @Column(name = "caddy_bag_id")
    private String caddyBagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caddy_bag_id", referencedColumnName = "gear_id", insertable = false, updatable = false)
    private GearEntity caddyBag;

    @OneToMany(mappedBy = "caddyBag")
    private Set<GearEntity> clubs = new HashSet<>();


    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private Set<ClubStructureEntity> clubStructures = new HashSet<>();

    @OneToMany(mappedBy = "gear", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private Set<GolferGearEntity> golferGears = new HashSet<>();

}
