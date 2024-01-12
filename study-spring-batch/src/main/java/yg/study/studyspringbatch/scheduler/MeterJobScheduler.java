package yg.study.studyspringbatch.scheduler;


import static yg.study.studyspringbatch.common.Constant.JOB_NAME_TWO;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
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
import yg.study.studyspringbatch.batchjob.metertasklet.MeterJobStep;
import yg.study.studyspringbatch.config.JobConfig;
import yg.study.studyspringbatch.domain.book.BookRepository;
import yg.study.studyspringbatch.domain.meter.MeterLogRepository;
import yg.study.studyspringbatch.domain.target.TargetEntity;
import yg.study.studyspringbatch.domain.target.TargetRepository;

@Log4j2
@Component
@RequiredArgsConstructor
@Profile("meter")
public class MeterJobScheduler {
    private final MeterJobStep meterJobStep;
    private final JobLauncher jobLauncher;
    private final TargetRepository targetRepository;
    private final MeterLogRepository meterLogRepository;

    @PostConstruct
    public void init() {
        targetRepository.deleteAll();
        meterLogRepository.deleteAll();
        var entities = IntStream.range(0, 10)
            .mapToObj(obj -> TargetEntity.builder()
                .targetName("targetName" + obj)
                .volume(0)
                .build()).collect(Collectors.toList());
        targetRepository.saveAll(entities);
    }



    @Scheduled(initialDelay = 1000, fixedDelay = 30000)
    public void meterSchedule() {
        Map<String, JobParameter> confMap = new HashMap<>();
        confMap.put("time", new JobParameter(DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss").format(ZonedDateTime.now())));
        JobParameters jobParameters = new JobParameters(confMap);
        try {
            log.info("=================== metering job Start == {}", jobParameters.getParameters());
            jobLauncher.run(meterJobStep.meteringJob(), jobParameters);
            log.info("=================== metering job End ================================");
        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException | org.springframework.batch.core.repository.JobRestartException | DuplicateKeyException e) {

            log.error(e.getMessage());
        }

    }


}
