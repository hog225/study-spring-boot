package yg.study.studyspringbatch.batchjob.metering.step.bean;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yg.study.studyspringbatch.batchjob.metering.step.chunk.MeterItemProcessor;
import yg.study.studyspringbatch.batchjob.metering.step.chunk.MeterItemReader;
import yg.study.studyspringbatch.batchjob.metering.step.chunk.MeterItemWriter;



@Configuration
@RequiredArgsConstructor
public class MeterChunkBean {

    @Bean
    @StepScope
    public ItemReader<String> meterReader(InputCodeBean inputCodeBean) {
        System.out.println(inputCodeBean.getInputCode());
        MeterItemReader itemReader = new MeterItemReader();
        return itemReader.getMeterItems();
    }

    @Bean
    @StepScope
    public ItemWriter<String> meterWriter(){
        return new MeterItemWriter();
    }
    @Bean
    @StepScope
    public ItemProcessor<String, String> meterProcessor(){
        return new MeterItemProcessor();
    }

}
