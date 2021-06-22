# Memo

JPA, Tymeleaf
- from 코드로 배우는 스프링 부트 웹프로젝트 (구멍가게 코딩단)
- https://github.com/rachel-kwak/springboot
 


### Thymeleaf
1. <p>[[${dto}]]</p> = <p><span th:text="${dto}"></span></p>

### 접속 
1. Blog http://localhost:8080/blog/

## JPA
### EAGER 와 LAZY
FETCH TYPE EAGER(Default) 의 경우 LEFT JOIN을 사용하여 results 에 Member, Board 모두 로드 된다. LAZY 의 경우엔 우선 Board 만 조회 한다. 그리고 Transactional 애노테이션(속성에 따라)의 도옴을 통해 getWriter가 호출 될 때 Member를 조회한다.

#### LAZY 단점
연관관계가 복잡해지면 당연히 쿼리도 많아 진다. 

## More
1. Class diagram 그려보기 
