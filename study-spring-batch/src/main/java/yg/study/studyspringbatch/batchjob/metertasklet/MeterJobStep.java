package yg.study.studyspringbatch.batchjob.metertasklet;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MeterStep {
  private final StepBuilderFactory stepBuilderFactory;
  private final JobBuilderFactory jobBuilderFactory;
  private final MeterTasklet meterTasklet;

  @Bean
  public Step meteringStep() {
    return stepBuilderFactory.get("meteringStep")
        .tasklet(meterTasklet)
        .build();
  }

  @Bean
  public Job meteringJob() {
    return jobBuilderFactory.get("meteringJob")
        .incrementer(new RunIdIncrementer())
        .start()
  }


}
