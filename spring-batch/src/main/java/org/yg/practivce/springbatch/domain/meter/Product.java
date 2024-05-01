package org.yg.practivce.springbatch.domain.meter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

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
