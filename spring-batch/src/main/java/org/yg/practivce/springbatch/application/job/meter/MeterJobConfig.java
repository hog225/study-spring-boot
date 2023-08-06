package org.yg.practivce.springbatch.application.job.meter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.yg.practivce.springbatch.application.job.meter.chunk.MeterItemProcessor;
import org.yg.practivce.springbatch.application.job.meter.chunk.MeterItemReader;
import org.yg.practivce.springbatch.application.job.meter.chunk.MeterItemWriter;
import org.yg.practivce.springbatch.domain.meter.Product;
import org.yg.practivce.springbatch.domain.meter.ProductMeters;
import org.yg.practivce.springbatch.domain.meter.repository.MeterRepository;
import org.yg.practivce.springbatch.domain.meter.repository.ProductRepository;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MeterJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final MeterRepository meterRepository;
    private final ProductRepository productRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job meterJob() {
        return jobBuilderFactory.get("meterJob")
                .incrementer(new RunIdIncrementer())
                .start(meterStep())
                .build();
    }

    @Bean
    public Step meterStep() {
        return stepBuilderFactory.get("meterUpdateStep")
                .<Product, ProductMeters>chunk(1)
                .reader(meterReader())
                .processor(meterProcessor())
                .writer(meterWriter())
                .faultTolerant()
                .skip(Exception.class)
                //.skipLimit(10) // 허용할 최대 Skip 횟수
                .skipLimit(Integer.MAX_VALUE)
                .build();
    }

    @Bean
    public ItemReader<Product> meterReader() {
        var products = productRepository.findAll();
        return new MeterItemReader(products);

    }

    @Bean
    public ItemProcessor<Product, ProductMeters> meterProcessor() {
        return new MeterItemProcessor();
    }

    @Bean
    public ItemWriter<ProductMeters> meterWriter() {
        return new MeterItemWriter(meterRepository);
    }
}
