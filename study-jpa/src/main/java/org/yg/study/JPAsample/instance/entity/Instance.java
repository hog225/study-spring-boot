package org.yg.study.JPAsample.instance.entity;


import lombok.*;
import org.yg.study.JPAsample.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
public class Instance extends BaseEntity {

    @Id
    @Column(name = "instance_id")
    private String instanceId;

    private String name;

    private String os;

    @ManyToOne
    @JoinColumn(name = "instance_id")
    private Instance autoScalingGroup;

    @OneToMany(mappedBy = "autoScalingGroup",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Instance> autoScalingGroupMembers;

    @OneToMany(mappedBy = "instance",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<InstanceNetwork> networks;

}
