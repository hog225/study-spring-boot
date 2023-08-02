package org.yg.study.JPAsample.entity;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name = "service")
public class ServiceEntity {

    @EmbeddedId
    private ServiceKey key;

    private String name;

}
