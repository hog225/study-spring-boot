package yg.study.studyspringbatch.batchjob.metering.step.chunk;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class MeterItemWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> items) throws Exception {
        System.out.println("writer Items " + items);
    }
}
