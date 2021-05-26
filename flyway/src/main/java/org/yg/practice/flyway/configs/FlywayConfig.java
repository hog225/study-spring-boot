package org.yg.practice.flyway.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


import org.flywaydb.core.Flyway;
@Configuration
public class FlywayConfig {
  
  @Value("${spring.datasource.url}")
  private String url;
  @Value("${spring.datasource.user}")
  private String user;
  @Value("${spring.datasource.password}")
  private String password;

  

  @Bean
  @Profile("test")
  public Flyway flyway(){

    System.out.println("database url : "+ this.url);
    Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
    return flyway;

  }

  public String getUrl(){
    return this.url;
  }
  
}
