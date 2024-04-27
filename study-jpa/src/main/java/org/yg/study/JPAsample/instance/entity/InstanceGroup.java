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
public class InstanceGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private String groupId;

    private String name;

    private String description;


}
