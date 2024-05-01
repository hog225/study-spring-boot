package org.yg.practivce.springbatch.application.job.meter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yg.practivce.springbatch.domain.meter.service.MeterService;

@RequiredArgsConstructor
@Component
@Slf4j
@StepScope
public class MeterUpdateTasklet implements Tasklet {
    private final MeterService meterService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("MeterUpdateTasklet execute");
        meterService.updateMeter();
        log.info("meter update sucess");
//        if (1 == 1)
//            throw new IllegalArgumentException("MeterUpdateTasklet Exception");
        return RepeatStatus.FINISHED;
    }

}
