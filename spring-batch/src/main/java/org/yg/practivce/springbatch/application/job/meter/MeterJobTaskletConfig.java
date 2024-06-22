package org.yg.practivce.springbatch.application.job.meter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MeterJobTaskletConfig {
    private final MeterUpdateTasklet meterTasklet;
    @Bean
    public Job meterTaskletJob(
            JobRepository jobRepository,
            Step meterTaskletStep
    ) {
        return new JobBuilder("meterJobTasklet", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(meterTaskletStep)
                .build();
    }

    @Bean
    public Step meterTaskletStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager
    ) {
        return new StepBuilder("meterUpdateTaskletStep", jobRepository)
                .tasklet(meterTasklet, transactionManager)
                .build();
    }

}
