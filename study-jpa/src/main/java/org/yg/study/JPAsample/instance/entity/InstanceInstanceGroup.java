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
@IdClass(InstanceInstanceGroupId.class)
public class InstanceInstanceGroup extends BaseEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "instance_id")
    private Instance instance;

    @Id
    @ManyToOne
    @JoinColumn(name = "group_id")
    private InstanceGroup instanceGroup;

}
