package yg.study.studyspringbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class FlowJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flowBatchJob() {
        return jobBuilderFactory.get("flowBatchJob")
                .incrementer(new RunIdIncrementer())
//                .start(flowStep1())
//                .on("COMPLETED").to(flowStep3())
//                .from(flowStep1())
//                .on("FAILED").to(flowStep2())
                .start(flowA())
                .next(flowStep3())
                .next(flowB())
                .next(flowStep6())
                .end()
                .build();
    }

    @Bean
    public Flow flowA() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flowA"); // simple flow
        flowBuilder
                .start(flowStep1())
                .next(flowStep2())
                .end();
        return flowBuilder.build();
    }

    @Bean
    public Flow flowB() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flowA"); // simple flow
        flowBuilder
                .start(flowStep4())
                .next(flowStep5())
                .end();
        return flowBuilder.build();
    }

    @Bean
    public Step flowStep1(){
        return stepBuilderFactory.get("flowStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(" >> flowStep1");
                        //throw new RuntimeException("step 1 fail!!!!!!!");
                        return RepeatStatus.FINISHED; // 한번만 실행 하고 종룔
                    }
                })
                .build();
    }

    @Bean
    public Step flowStep2(){
        return stepBuilderFactory.get("flowStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(" >> flowStep2");
                        return RepeatStatus.FINISHED; // 한번만 실행 하고 종룔
                    }
                })
                .build();
    }

    @Bean
    public Step flowStep3(){
        return stepBuilderFactory.get("flowStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(" >> flowStep3");
                        return RepeatStatus.FINISHED; // 한번만 실행 하고 종룔
                    }
                })
                .build();
    }

    @Bean
    public Step flowStep4(){
        return stepBuilderFactory.get("flowStep4")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(" >> flowStep4");
                        return RepeatStatus.FINISHED; // 한번만 실행 하고 종룔
                    }
                })
                .build();
    }

    @Bean
    public Step flowStep5(){
        return stepBuilderFactory.get("flowStep5")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(" >> flowStep5");
                        return RepeatStatus.FINISHED; // 한번만 실행 하고 종룔
                    }
                })
                .build();
    }


    @Bean
    public Step flowStep6(){
        return stepBuilderFactory.get("flowStep6")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(" >> flowStep6");
                        return RepeatStatus.FINISHED; // 한번만 실행 하고 종룔
                    }
                })
                .build();
    }


}
