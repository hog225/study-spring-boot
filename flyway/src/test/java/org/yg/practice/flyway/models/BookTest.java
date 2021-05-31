//V3__bulk_update_books 가 주석처리 되어 있기 때문에 해당 Test 일단 주석
package org.yg.practice.flyway.models;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.yg.practice.flyway.configs.FlywayConfig;
import org.yg.practice.flyway.repositorys.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import static org.assertj.core.api.Assertions.assertThat;

// Class Pass 내 Data에 관련된 테스트만 한다. 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class BookTest extends ResetDatabase {

  @Autowired
  private BookDao bookDao;

  @Value("${spring.datasource.url}")
  private String testValue;

  @Test
  public void createSuccess(){
    //Given 준비 
    ////사전 준비
    Book book = new Book();
    book.setId(1L);
    book.setName("flyway");
    book.setAuthor("authorrrr");

    //When 어떤 상황이 발생 한다면 
    ////객체 생성
    bookDao.save(book);

    //Then 어떤 결과 
    ////객체 생성 검증
    //assertThat("test").isEqualTo("test");
    Optional<Book> bookFromDB = bookDao.findById(1L);
    assertThat(bookFromDB.isPresent()).isEqualTo(true);
    assertThat(bookFromDB.get().getName()).isEqualTo(book.getName());
    assertThat(bookFromDB.get().getAuthor()).isEqualTo(book.getAuthor());
    System.out.println(bookFromDB.get().getAuthor());
    

    System.out.println("BookTest.createSuccess !");
  }

  @Test
  public void test(){
      FlywayConfig flywayConfig = new FlywayConfig();
      System.out.println("flywayConfig url : " + flywayConfig.getUrl());
  }

  @Test
  public void valueTest(){
      System.out.println("ULR: "+ testValue);
  }
  
}
