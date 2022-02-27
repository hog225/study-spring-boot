package yg.study.studyspringbatch.scheduler;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import yg.study.studyspringbatch.config.JobConfig;
import yg.study.studyspringbatch.domain.book.Book;
import yg.study.studyspringbatch.domain.book.BookRepository;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor
public class JobSchduler {
    private final JobConfig meteringJob;
    private final JobLauncher jobLauncher;
    private final BookRepository bookRepository;

    //@Scheduled(initialDelay = 10000, fixedDelay = 30000)
    @Scheduled(cron = "3/10 * * * * *")
    public void runJob() {

        Map<String, JobParameter> confMap = new HashMap<>();
        confMap.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(confMap);

        try {

            jobLauncher.run(meteringJob.job(), jobParameters);
            Book book = Book.create("33", ZonedDateTime.now());
            bookRepository.save(book);

        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException | org.springframework.batch.core.repository.JobRestartException e) {

           log.error(e.getMessage());
        }
    }





}
