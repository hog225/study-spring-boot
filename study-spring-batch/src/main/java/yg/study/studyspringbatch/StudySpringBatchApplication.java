package yg.study.studyspringbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
@EntityScan("yg.study.studyspringbatch")
public class StudySpringBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudySpringBatchApplication.class, args);
	}

}
