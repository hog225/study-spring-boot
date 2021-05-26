package org.yg.practice.flyway.models;

import org.flywaydb.core.Flyway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.yg.practice.flyway.configs.*;

// 다른 Test Instance 가 실행 되기 전에 수행 되게 하기 위한 Annotation
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ResetDatabase {
  @Autowired
  private Flyway flyway;

  @BeforeEach
  public void setUp(){
    
    flyway.clean();
    flyway.migrate();
    
    
    System.out.println("ResetDatabase.setup !");

  }
}
