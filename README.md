# JPA Study 

## reference 
- inflearn 실전! 스프링 데이터 JPA



## gradle 
- gradlew.bat dependencies --configuration compileClasspath - 의존 관계확인 


## JPA
- JPA vs Spring Data JPA
    - JPA: Entity Manager를 User 가 Control 해야 한다. 
    - Spring Data JPA: Spring 이 알아서 Entity Manager를 이용해 CRUD Paging 등 구현 한다.
- HikariCP - database connection pool 로 성능 issue 있을시 설정을 해야 한다. 
- entity 의 경우 Default 생성자가 있어야 하는데 Proxy 기술이 사용되기 위해서는 접근자가 Protected 는 되어야 한다. 
- dirty checking: Proxy 가 @Transactional 에 의 Commit 되는데 변경을 감지해서 알아서 Update 쿼리를 날린다. basicCRUD 참조
- @SpringBootApplication 가 있으면 @EnableJpaRepositories 설정을 안해줘도 되는데 .. SpringBootApplication 어노테이션이 기본적으로 org.yg.study.JPAsample <br />
하위 패키지에 대해 EnableJpaRepositories 설정을 적용 해준다.
- [QueryMethod](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods)
- Named Query : Entity 에 Query 이름과 JPQL을 지정하여 Query 이름으로 쿼리를 할수 있다(JPA 의 경우) 거의 안씀.. NamedQuery를 쓰면 프로그램 로딩시에 SQL 을 파싱함으로 SQL 문에 오타가 있는지 알 수 있다. 
  Repository 에 @Query 에 SQL 문을 쓰는 경우도 마찬가지로 로딩시 SQL 문 오타를 잡을 수 있음  
- JPQL
  - = :[Parameter Name 이름기반] "select m from Member m where m.username =: username"
  - = ?[Parameter Order 위치기반] "select m from Member m where m.username =? 1"
  - DTO 로 반환시 "select new org.yg.study.JPAsample.dto.MemberDto(m.id, m.username, t.name) from Member m join Team t".
  or List<Object[]> 형식으로 ...
- QueryDSL **** 중요 !! 실무에서는 이게 가장 깔끔하다고함 
- 반환데이터가 없을지 있을지 모를땐  Optional(orElse, orElseGet) 을 사용하자 
- [Spring data JPA 지원하는 Retrun Type](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-return-types)
- jpa.properties.hibernate.dialect: org.hibernate.dialect.O.... 해당 DB에 맞게 Query가 나감 
