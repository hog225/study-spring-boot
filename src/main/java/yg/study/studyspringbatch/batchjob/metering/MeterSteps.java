package yg.study.studyspringbatch.batchjob.metering;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yg.study.studyspringbatch.batchjob.metering.tasklets.CustomTasklet;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MeterSteps {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step meterGet(){
        return stepBuilderFactory.get("meterGet")
                .tasklet(new CustomTasklet())
                .allowStartIfComplete(false)
                .startLimit(3)
                .build();
    }

    @Bean
    public Step chunkStep() {
        return stepBuilderFactory.get("chunkStep")
            // chunk 기반의 작업
            .<String, String>chunk(3)
            .reader(new ListItemReader<>(Arrays.asList("item1", "item2", "item3", "item4")))
            .processor(new ItemProcessor<String, String>() {
                @Override
                public String process(String item) throws Exception {
                    Thread.sleep(300);
                    System.out.println("item " +  item);
                    return "my" + item;
                }
            })
            .writer(new ItemWriter<String>() {
                @Override
                public void write(List<? extends String> items) throws Exception {
                    Thread.sleep(300);
                    System.out.println("writer Items " + items);
                }
            })
            .build();
    }


}
