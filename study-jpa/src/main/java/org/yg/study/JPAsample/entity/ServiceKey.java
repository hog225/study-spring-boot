package org.yg.study.JPAsample.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;


@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceKey implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(length = 10)
    private String serviceId;
    @Column(length = 12)
    private String serviceType;




}
