package yg.study.studyspringbatch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yg.study.studyspringbatch.batchjob.metering.step.MeterSteps;

import static yg.study.studyspringbatch.common.Constant.JOB_NAME;

@RequiredArgsConstructor
@Configuration
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final MeterSteps meterSteps;

    @Bean
    public Job job(){
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(meterSteps.chunkStep())
                .next(meterSteps.chunkStep())
                .next(meterSteps.meterCreateStep())
                .build();
    }
}
