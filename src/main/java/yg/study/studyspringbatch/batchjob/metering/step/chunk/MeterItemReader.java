package yg.study.studyspringbatch.batchjob.metering.step.chunk;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ListItemReader;

import java.util.Arrays;

public class MeterItemReader {

    public ItemReader<String> getMeterItems() {
        System.out.println(" Meter reader ");
        return new ListItemReader<>(Arrays.asList("item1", "item2", "item3", "item4"));
    }
}
