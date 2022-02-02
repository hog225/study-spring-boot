package yg.study.studyspringbatch.batchjob.metering;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class MeteringJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final MeterSteps meterSteps;

    @Bean
    public Job job(){
        return jobBuilderFactory.get("helloJob1")
                .start(meterSteps.meterGet())
                .next(meterSteps.chunkStep())
                .incrementer(new RunIdIncrementer())
                //.validator(new ValidationConfiguration())
                .preventRestart()
                .build();
    }
}
