package org.yg.study.JPAsample.instance.entity;

import javax.persistence.Column;
import java.io.Serializable;

public class InstanceInstanceGroupId implements Serializable {

    @Column(name = "instance_id")
    private String instance;

    @Column(name = "group_id")
    private Long instanceGroup;
}
