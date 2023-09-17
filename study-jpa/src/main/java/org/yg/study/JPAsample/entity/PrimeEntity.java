package org.yg.study.JPAsample.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name = "prime_data")
public class PrimeEntity {

    @Id
    private String id;

    private String name;



}
