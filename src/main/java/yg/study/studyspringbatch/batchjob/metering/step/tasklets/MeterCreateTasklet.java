package yg.study.studyspringbatch.batchjob.metering.step.tasklets;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import yg.study.studyspringbatch.domain.book.Book;
import yg.study.studyspringbatch.domain.book.BookRepository;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;

@RequiredArgsConstructor
public class MeterCreateTasklet implements Tasklet {


    @Override
    @Transactional
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        String stepName = contribution.getStepExecution().getStepName();
        System.out.println(" >> Custom Tasklet step 11111 step name = " + stepName);


        //business Logic
        return RepeatStatus.FINISHED;
    }
}
