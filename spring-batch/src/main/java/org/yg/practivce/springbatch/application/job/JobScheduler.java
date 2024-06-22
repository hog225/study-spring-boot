package org.yg.practivce.springbatch.application.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobScheduler {
    private final JobLauncher jobLauncher;
    private final Job meterJob;
    private final Job meterTaskletJob;
    private final Job createArticleJob;

    @Scheduled(fixedDelay = 1000000, initialDelay = 100000000)
    public void runMeterJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        System.out.println("Meter Job");
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder()
                .addString("datetime", String.valueOf(System.currentTimeMillis()));

        jobLauncher.run(meterJob, jobParametersBuilder.toJobParameters());
    }

    @Scheduled(fixedDelay = 1000000, initialDelay = 100000)
    public void runMeterTaskletJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        System.out.println("Meter Tasklet Job");
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder()
                .addString("datetime", String.valueOf(System.currentTimeMillis()));

        jobLauncher.run(meterTaskletJob, jobParametersBuilder.toJobParameters());
    }

    @Scheduled(fixedDelay = 1000000, initialDelay = 10)
    public void runCreateArticleJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        System.out.println("Create Article Job");
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder()
                .addString("datetime", String.valueOf(System.currentTimeMillis()));

        jobLauncher.run(createArticleJob, jobParametersBuilder.toJobParameters());
    }
}
