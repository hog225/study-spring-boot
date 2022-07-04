package yg.study.studyspringbatch.config;

import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import yg.study.studyspringbatch.scheduler.QuartzJob;

import javax.annotation.PostConstruct;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
@Profile("quartz-batch")
public class QuartzConfig {

    private final SchedulerFactoryBean schedulerFactory;

    @PostConstruct
    public void scheduled() throws SchedulerException {
        JobDataMap map1 = new JobDataMap(Collections.singletonMap("num", "1"));
        JobDataMap map2 = new JobDataMap(Collections.singletonMap("num", "2"));
        JobDetail jobONE = jobDetail("hello1", DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss").format(ZonedDateTime.now()), map1);
        JobDetail job2 = jobDetail("hello2", DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss").format(ZonedDateTime.now()), map2);
        SimpleTrigger trigger1 = trigger("trigger1", "trigger-group" + DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss").format(ZonedDateTime.now()));
        SimpleTrigger trigger2 = trigger("trigger2", "trigger-group" + DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss").format(ZonedDateTime.now()));
        schedulerFactory.getObject().scheduleJob(jobONE, trigger1);
        schedulerFactory.getObject().scheduleJob(job2, trigger2);
    }

    public JobDetail jobDetail(String name, String group, JobDataMap dataMap) {
        JobDetail job = JobBuilder.newJob(QuartzJob.class)
                .withIdentity(name, group)
                .withDescription("simple hello job")
                .usingJobData(dataMap)
                .build();
        return job;
    }

    public SimpleTrigger trigger(String name, String group) {
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(name, group)
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(10))
                .withDescription("hello my trigger")
                .build();
        return trigger;
    }
}