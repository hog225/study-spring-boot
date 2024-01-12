package yg.study.studyspringbatch.scheduler;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import yg.study.studyspringbatch.config.JobConfig;
import yg.study.studyspringbatch.domain.book.Book;
import yg.study.studyspringbatch.domain.book.BookRepository;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static yg.study.studyspringbatch.common.Constant.JOB_NAME_TWO;

@Log4j2
@Component
@RequiredArgsConstructor
@EnableSchedulerLock(defaultLockAtMostFor = "PT10S")
@Profile("chunk")
public class JobSchduler {
    private final JobConfig meteringJob;
    private final JobLauncher jobLauncher;
    private final BookRepository bookRepository;
    private final JobExplorer jobExplorer;

    //@Scheduled(initialDelay = 10000, fixedDelay = 30000)
//    @Scheduled(cron = "3/10 * * * * *")
//    public void runJob() {
//
//        Map<String, JobParameter> confMap = new HashMap<>();
//        confMap.put("time", new JobParameter(DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss").format(ZonedDateTime.now())));
//        JobParameters jobParameters = new JobParameters(confMap);
//
//        try {
//            Set<JobExecution> excutions = jobExplorer.findRunningJobExecutions(meteringJob.job().getName());
//            if (excutions.size() > 1) {
//                throw new JobExecutionAlreadyRunningException("already running ");
//            }
//
//            jobLauncher.run(meteringJob.job(), jobParameters);
//            Book book = Book.create("33", ZonedDateTime.now());
//            bookRepository.save(book);
//
//        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException
//                | JobParametersInvalidException | org.springframework.batch.core.repository.JobRestartException | DuplicateKeyException e) {
//
//           log.error(e.getMessage());
//        }
//    }

    @Scheduled(cron = "4/10 * * * * *")
    @SchedulerLock(name=JOB_NAME_TWO,lockAtLeastFor = "PT3S", lockAtMostFor = "PT3S")
    public void pureBatch() {
        Map<String, JobParameter> confMap = new HashMap<>();
        confMap.put("time", new JobParameter(DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss").format(ZonedDateTime.now())));
        JobParameters jobParameters = new JobParameters(confMap);
        try {
            log.info("=================== metering job Start == {}", jobParameters.getParameters());
            jobLauncher.run(meteringJob.job2(), jobParameters);
            log.info("=================== metering job End ================================");
        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException | org.springframework.batch.core.repository.JobRestartException | DuplicateKeyException e) {

            log.error(e.getMessage());
        }

    }


}
