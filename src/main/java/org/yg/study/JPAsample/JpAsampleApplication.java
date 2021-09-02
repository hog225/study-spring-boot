package org.yg.study.JPAsample;

import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JpAsampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpAsampleApplication.class, args);
	}

	// jpa creatBy 에 데이터 늘때 실제로는 세션에서 꺼내서 넣어야 겠지 >?
	@Bean
	public AuditorAware<String> auditorProvider(){
		return () -> Optional.of(UUID.randomUUID().toString());
	}

}
