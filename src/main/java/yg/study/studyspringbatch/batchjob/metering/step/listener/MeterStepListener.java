package yg.study.studyspringbatch.batchjob.metering.step.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import yg.study.studyspringbatch.batchjob.metering.step.bean.InputCodeBean;

@RequiredArgsConstructor
public class MeterStepListener {
    private final InputCodeBean inputCodeBean;

    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("after step call");
        String inputCode = inputCodeBean.getInputCode();
        System.out.println(inputCode);
        return stepExecution.getExitStatus();

    }

}
