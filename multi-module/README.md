# Spring Boot Multi Module 

1. API
   1. Web 관련된 내용
   2. Filter, Interceptor, Controller
2. domain
   1. repository, entity, service
3. batch 
   1. Spring batch 관련된 사항들
4. external
   1. 외부 API 
5. common ?
   1. exception handler ? 공통으로 하는게 나을까? 
   2. APIExceptionHandler, DomainExceptionHandler 따로 ?




## maven build
1. mvn clean install -pl api -am
   1. -am 상위 모듈 까지 같이 빌드 


## 도옴되는 
1. https://github.com/ihoneymon/multi-module

## API 
1. localhost:8080/API/1
2. localhost:8080/book/1

## TO-DO 
1. ExceptionHandler Module 별로 ? 
2. Stage 에 따른 Application.yml