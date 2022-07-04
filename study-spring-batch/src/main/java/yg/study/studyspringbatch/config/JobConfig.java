package yg.study.studyspringbatch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yg.study.studyspringbatch.batchjob.metering.step.MeterSteps;

import static yg.study.studyspringbatch.common.Constant.JOB_NAME;
import static yg.study.studyspringbatch.common.Constant.JOB_NAME_TWO;

@RequiredArgsConstructor
@Configuration
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final MeterSteps meterSteps;

    @Bean
    public Job job(){
        return jobBuilderFactory.get(JOB_NAME)
                .start(meterSteps.chunkStep())
                .next(meterSteps.chunkStep())
                .next(meterSteps.meterCreateStep())
                .build();
    }

    @Bean
    public Job job2(){
        return jobBuilderFactory.get(JOB_NAME_TWO)
                .incrementer(new RunIdIncrementer())
                .start(meterSteps.meterCreateStepTwo())
                .build();
    }
}
