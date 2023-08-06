package org.yg.practivce.springbatch.application.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yg.practivce.springbatch.application.job.meter.MeterJobConfig;

@Component
@RequiredArgsConstructor
public class JobScheduleer {
    private final JobLauncher jobLauncher;
    private final MeterJobConfig meterJobConfig;

    @Scheduled(fixedDelay = 1000000, initialDelay = 10)
    public void runMeterJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        System.out.println("Meter Job");
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder()
                .addString("datetime", String.valueOf(System.currentTimeMillis()));

        jobLauncher.run(meterJobConfig.meterJob(), jobParametersBuilder.toJobParameters());
    }
}
