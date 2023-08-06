package org.yg.practivce.springbatch.domain.meter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productKey;

    private String name;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
