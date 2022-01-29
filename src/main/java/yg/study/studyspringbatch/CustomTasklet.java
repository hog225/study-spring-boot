package yg.study.studyspringbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class CustomTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        String stepName = contribution.getStepExecution().getStepName();
        System.out.println(" >> Custom Tasklet step 11111 ");

        //business Logic
        return RepeatStatus.FINISHED;
    }
}
