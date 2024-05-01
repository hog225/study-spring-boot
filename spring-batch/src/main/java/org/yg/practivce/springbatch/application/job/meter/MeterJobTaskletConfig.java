package org.yg.practivce.springbatch.application.job.meter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.yg.practivce.springbatch.domain.meter.repository.MeterRepository;
import org.yg.practivce.springbatch.domain.meter.repository.ProductRepository;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MeterJobTaskletConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final MeterUpdateTasklet meterTasklet;
    private final MeterRepository meterRepository;
    private final ProductRepository productRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job meterTaskletJob() {
        return jobBuilderFactory.get("meterJobTasklet")
                .incrementer(new RunIdIncrementer())
                .start(meterTaskletStep())
                .build();
    }

    @Bean
    public Step meterTaskletStep() {
        return stepBuilderFactory.get("meterUpdateTaskletStep")
                .tasklet(meterTasklet)
                .build();
    }

}
