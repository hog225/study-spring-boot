package org.yg.practivce.springbatch.domain.meter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yg.practivce.springbatch.domain.meter.Product;
import org.yg.practivce.springbatch.domain.meter.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public void updateOrSaveProduct(Product product) {
        productRepository.save(product);
    }
}
