package org.yg.practivce.springbatch.application.job.meter.chunk;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.yg.practivce.springbatch.domain.meter.ProductMeters;
import org.yg.practivce.springbatch.domain.meter.repository.MeterRepository;

public class MeterItemWriter implements ItemWriter<ProductMeters> {
    private final MeterRepository meterRepository;

    public MeterItemWriter(MeterRepository meterRepository) {
        this.meterRepository = meterRepository;
    }


    @Override
    public void write(Chunk<? extends ProductMeters> items) {

        for (var item : items) {

            var product = item.getProduct();
            var meters = item.getMeters();
            System.out.println("writing item " + product + " " + meters);
            for(var meter : meters) {
                meterRepository.saveAndFlush(meter);
            }
        }


    }
}
