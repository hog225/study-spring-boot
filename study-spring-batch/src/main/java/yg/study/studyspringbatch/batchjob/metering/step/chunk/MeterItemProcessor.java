package yg.study.studyspringbatch.batchjob.metering.step.chunk;


import org.springframework.batch.item.ItemProcessor;

public class MeterItemProcessor implements ItemProcessor<String, String> {

    @Override
    public String process(String item) throws Exception {
        System.out.println("item " +  item);
        return "my" + item;
    }
}
