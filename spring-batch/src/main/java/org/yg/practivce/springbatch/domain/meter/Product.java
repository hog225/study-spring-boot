package org.yg.practivce.springbatch.domain.meter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;


import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@DynamicUpdate
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productKey;

    private String name;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
