package org.yg.practivce.springbatch.application.job.meter.chunk;

import org.springframework.batch.item.ItemReader;
import org.yg.practivce.springbatch.domain.meter.Product;

import java.util.LinkedList;
import java.util.List;

public class MeterItemReader implements ItemReader<Product> {
    private final List<Product> products = new LinkedList<>();
    public MeterItemReader(List<Product> products) {
        this.products.addAll(products);
    }

    @Override
    public Product read() throws Exception {
        if (!products.isEmpty()) {
            var it =  products.remove(0);
            System.out.println("reading item " + it);
            return it;
        }
        return null;
    }

}
