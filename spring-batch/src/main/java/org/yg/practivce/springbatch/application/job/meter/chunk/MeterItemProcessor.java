package org.yg.practivce.springbatch.application.job.meter.chunk;

import org.springframework.batch.item.ItemProcessor;
import org.yg.practivce.springbatch.domain.meter.Meter;
import org.yg.practivce.springbatch.domain.meter.Product;
import org.yg.practivce.springbatch.domain.meter.ProductMeters;

import java.util.LinkedList;
import java.util.Random;

public class MeterItemProcessor implements ItemProcessor<Product, ProductMeters> {


    @Override
    public ProductMeters process(Product item) throws Exception {

        try {
            var count = new Random().nextInt(100);
            var meterCount = new Random().nextInt(3);
            var meters = new LinkedList<Meter>();
            System.out.println("processing item " + item);
            for (int i = 0; i < 2; i++) {
                var meter = Meter.builder()
                        .count(count)
                        .name("meter-" + i + item.getProductKey())
                        .productKey(item.getProductKey())
                        .build();
                meters.add(meter);
            }

            if (item.getName().equals("p-3")) {
                /**
                 *  하기 Exception 이 발생 하더라도 처리된 Chunk 는 Rollback 되지 않는다.
                 */
                throw new RuntimeException("product3 is not allowed");
            }

            if (item.getName().equals("p-2")) {
                /**
                 *  ItemWriter 에서 Exception 이 발생한다. skip 옵션으로 인해 Exception 발생해도 해당 chunk 는 롤백 되고 Job 은 계속 이어진다.
                 */
                meters.get(0).setName("meter-" + "1" + item.getProductKey());
            }

            return new ProductMeters(item, meters);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
