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
public class InstanceNetwork extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "network_id")
    private Long networkId;

    private String privateIp;

    private String floatingIp;

    @ManyToOne
    @JoinColumn(name = "instance_id")
    private Instance instance;



}
