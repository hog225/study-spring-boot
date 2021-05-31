package org.yg.practice.flyway.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.flywaydb.core.Flyway;

@Configuration
//@PropertySource("file:src/main/resources/application.properties")
//@PropertySource(value = "file:/src/test/resources/properties/application.properties")
@PropertySource("classpath:application-test.properties") // 요게없으면 Path를 못 찾았다. 이유는 ?
public class FlywayConfig {
  
  @Value("${spring.datasource.url}")
  private String url;
  @Value("${spring.datasource.username}")
  private String user;
  @Value("${spring.datasource.password}")
  private String password;

  

  @Bean
  //@Profile("test")
  public Flyway flyway(){

    System.out.println("url: " + url);
    System.out.println("user: " + user);
    Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
    return flyway;

  }

  public String getUrl(){
    return this.url;
  }
  
}
