package yg.study.studyspringbatch.scheduler;

import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Log4j2
public class QuartzJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap map = context.getJobDetail().getJobDataMap();
        log.info("RequestContractJob execute invoked, job-detail-key:{}, fired-time:{}, num:{}",
                context.getJobDetail().getKey(), context.getFireTime(), map.getInt("num"));
        log.info("RequestContractJob execute complete");
    }
}
