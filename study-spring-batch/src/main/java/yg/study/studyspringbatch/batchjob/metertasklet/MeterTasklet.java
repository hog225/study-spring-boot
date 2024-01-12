package yg.study.studyspringbatch.batchjob.metertasklet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import yg.study.studyspringbatch.domain.meter.MeterService;

@RequiredArgsConstructor
@Component
@Slf4j
@StepScope
public class MeterTasklet implements Tasklet {

  private final MeterService meterService;


  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    String code = contribution.getStepExecution().getJobExecution()
        .getJobParameters().getString("code");
    log.info("MeterUpdateTasklet Start {}", code);
    meterService.updateMeter(code);
    return RepeatStatus.FINISHED;
  }

}
