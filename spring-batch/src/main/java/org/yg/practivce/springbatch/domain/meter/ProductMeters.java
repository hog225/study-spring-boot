package org.yg.practivce.springbatch.domain.meter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductMeters {
    private Product product;
    private List<Meter> meters = new ArrayList<>();
}
