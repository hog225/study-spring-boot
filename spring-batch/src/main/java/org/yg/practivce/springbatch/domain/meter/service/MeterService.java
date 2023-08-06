package org.yg.practivce.springbatch.domain.meter.service;

import antlr.collections.impl.IntRange;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.yg.practivce.springbatch.domain.meter.Meter;
import org.yg.practivce.springbatch.domain.meter.Product;
import org.yg.practivce.springbatch.domain.meter.repository.MeterRepository;
import org.yg.practivce.springbatch.domain.meter.repository.ProductRepository;

import javax.annotation.PostConstruct;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class MeterService {
    private final ProductRepository productRepository;
    private final MeterRepository meterRepository;

    @PostConstruct
    public void init() {
        System.out.println("MeterService.init : create product if not exists, delete meter if exists");
        var products = productRepository.findAll();
        if (products.isEmpty()) {
            var productList = IntStream.range(0, 5).mapToObj(i -> {
                return Product.builder()
                        .name("p-" + i)
                        .productKey("product" + i)
                        .createdAt(ZonedDateTime.now())
                        .updatedAt(ZonedDateTime.now())
                        .build();
            }).collect(Collectors.toList());
            productRepository.saveAll(productList);
        }

        meterRepository.deleteAll();

    }
}
