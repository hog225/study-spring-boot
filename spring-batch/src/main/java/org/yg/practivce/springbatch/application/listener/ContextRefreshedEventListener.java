package org.yg.practivce.springbatch.application.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Set;

// Batch 실행중 실패시 자동 생성된 Batch DB 에 데이터가 남는다.
// 아래 코드는 해당 DB를 Batch 실행전 조회하여 실패일 경우 실패에 대응하는 코드이다.
@Configuration
@Slf4j
@RequiredArgsConstructor
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    private final JobExplorer jobExplorer;
    private final JobRepository jobRepository;

    // Batch 가 실행되기 이전에 해당 ID 의 Batch의 상태를 보고 실행 상태가 Fail이라면 DB 를 Stoped로 변경하고 Batch를 이어나간다.
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){
        log.info("find stop runnung jobs................");
        for (String jobName: jobExplorer.getJobNames()){
            Set<JobExecution> runningJobExcutions = jobExplorer.findRunningJobExecutions(jobName);
            for (JobExecution jobExcution: runningJobExcutions){
                log.warn("!!!!!!!!!!{}", jobExcution.getJobId());
                // job excution 중지
                jobExcution.setStatus(BatchStatus.STOPPED);
                jobExcution.setEndTime(new Date());
                for (StepExecution stepExecution: jobExcution.getStepExecutions()){
                    if (stepExecution.getStatus().isRunning()){
                        stepExecution.setStatus(BatchStatus.STOPPED);
                        stepExecution.setEndTime(new Date());
                        jobRepository.update(stepExecution);

                    }
                }
                jobRepository.update(jobExcution);


            }

        }
        log.info("stop runnung jobs................");
    }

}
