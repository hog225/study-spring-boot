package yg.study.studyspringbatch.batchjob.metering.step;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yg.study.studyspringbatch.batchjob.metering.step.bean.InputCodeBean;
import yg.study.studyspringbatch.batchjob.metering.step.bean.MeterChunkBean;
import yg.study.studyspringbatch.batchjob.metering.step.listener.MeterStepListener;
import yg.study.studyspringbatch.batchjob.metering.step.tasklets.MeterCreateTasklet;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MeterSteps {

    private final StepBuilderFactory stepBuilderFactory;
    private final MeterChunkBean meterChunkBean;
    private final InputCodeBean inputCodeBean;

    @Bean
    public Step meterCreateStep(){
        return stepBuilderFactory.get("MeterCreate")
                .tasklet(new MeterCreateTasklet())
                .allowStartIfComplete(true)
//                .startLimit(3)
                .build();
    }

    @Bean
    public Step meterCreateStepTwo(){
        return stepBuilderFactory.get("MeterCreateTwo")
                .tasklet(new MeterCreateTasklet())
                .build();
    }

    @Bean
    public Step chunkStep() {
        return stepBuilderFactory.get("chunkStep")
            // chunk 기반의 작업
                .allowStartIfComplete(true)
            .<String, String>chunk(3)
            .reader(meterChunkBean.meterReader(inputCodeBean))
            .processor(meterChunkBean.meterProcessor())
            .writer(meterChunkBean.meterWriter())
                .listener(new MeterStepListener(inputCodeBean))
            .build();
    }


}
