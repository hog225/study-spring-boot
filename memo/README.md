# Memo

JPA, Tymeleaf, Spring Security
- from 코드로 배우는 스프링 부트 웹프로젝트 (구멍가게 코딩단)
- https://github.com/rachel-kwak/springboot
 


## Thymeleaf
...

## Test 
1. Blog http://localhost:8080/blog/list // JPA queryDSL
2. Board http://localhost:8080/board/list // JPA JPQL
3. http://localhost:8080/org.yg.memo-test/uploadEx //fileUpload
3. Movie http://localhost:8080/movie/list // JPA 양방향
4. sample http://localhost:8080/sample/ex2
5. Club Member http://localhost:8080/sample/member //Spring Security FormLogin, OAuthLogin
6. Note http://localhost:8080/notes/2 // Spring Security API Login

## JPA
### N:1 관계
1. @ManyToOne 이용 
2. PK를 기준으로 잡고 데이터를 모델링 예) 회원 - PK 게시글 - FK(회원의 PK)
3. 해석은 FK 에서 예) 게시글 과 회원은 N:1 관계
4. FK 를 가진 Entity 가 PK를 가진 Entity를 참조 하는게 하는게 DB 모델링과 동일한 구조가 되기 때문에 이런 방식으로 한다.
    ```
    member <--- board
    ```
5. N+1 문제 
    > 한번의 쿼리로 N개의 데이터를 가져 왔는데 N개의 데이터를 처리하기 위해서 필요한 추가적인 쿼리가 각 N개에 대해서 수행되는 상황

### EAGER 와 LAZY
FETCH TYPE EAGER(Default) 의 경우 LEFT JOIN을 사용하여 results 에 Member, Board 모두 로드 된다. LAZY 의 경우엔 우선 Board 만 조회 한다. 그리고 Transactional 애노테이션(속성에 따라)의 도옴을 통해 getWriter가 호출 될 때 Member를 조회한다.

#### LAZY 단점
연관관계가 복잡해지면 당연히 쿼리도 많아 진다.

### M:N 
1. @ManyToMany 사용(쉽지않은 방법) or 별도 Entity 생성후 @ManyToOne으로 
1. 영화와 회원 ,학생과 수업, 상품과 상품 카테고리, 상품과 회원
2. 테이블 설계가 논리적으로 되지 않으니 매핑 테이블을 이용  
3. 매핑 테이블의 특징 
    - 매핑 테이블 이전에 다른 테이블이 먼저 존재 해야 한다. 
    - 명사가 아닌 동사나 히스토리에 대한 데이터를 보관한다. 
    - 매핑 테이블은 중간에서 양쪽의 PK를 참조한다. 
4. 영화(PK), 회원(PK), review(FK[영화, 회원], 매핑테이블 "평점을 준다.")
5. revieRepositoryTests.java 참조  (N+1 문제 해결 )
#### 양방향 설계 
> 양방향 설계가 어렵긴 하지만 연관관계를 잘 설계한다면 @Query를 최소화 하고 객체지향에 맞는 코드를 생산할 수 있다.

양방향은 PK 입장에서 FK 의 Entity를 참조 

@OneToMany를 PK 쪽 Entity에서 FK 를 가진 Entity를 참조하는데 이용함 OneToMany의 경우 @JoinTable등을 이용해 별도의 테이블을 지정하거나. 
MappedBy 속성을 이용해 하위 엔티티 설정을 추가할 수 있다. MappedBy 속성을 이용하면 자기 자신은 연관관계의 주인이 아님을 DB에 알려줄 수 있다.

- 양방향 설계의 장점 
   - Pk 쪽 Entity 객체에 FK 쪽 Entity가 존재하도록 구성하고 이를 한번에 저장 할 수 있음 
   - PK 쪽 Entity 삭제시 그 안에 속하는 FK 쪽 Entity도 삭제 가능 
- 양방향 설계의 단점
   - 트랜젝션이 일어난다. 
   - FK 쪽 삭제시 PK 쪽 Entity에서 LIST 내용을 삭제하느 작업이 필요 하다. 

    
## fileUpload
1. upload 방식 
   - commons-fileupload 라이브러리를 사용
   - Servlet 3버전부터 추가된 자체적인 파일 업로드 라이브러리를 사용 
   
2. Multipart
   - 웹 클라이언트가 요청을 보낼 때, http 프로토콜의 바디 부분에 데이터를 여러 부분으로 나눠서 보내는 것.
   - Spring 에서 이를 처리하도록 MultipartFile 인터페이스를 제공한다. 

## ToDo
1. Class diagram 그려보기 
