package com.yg.api;

import com.yg.external.publicapis.property.PublicProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.yg.domain", "com.yg.api", "com.yg.external"})
@EntityScan(basePackages = {"com.yg.domain"})
@EnableJpaRepositories(basePackages = {"com.yg.domain"})
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}
