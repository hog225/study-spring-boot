독자 (Reader), 작가(Writer) 가 존재하는 웹소설 플랫폼을 구축한다고 가정한다.

작가는 웹소설을 편당 금액과 함께 등록할 수 있으며,
독자는 돈을 지불하여 이를 감상할 수 있다.
관련하여 기본적인 도서에 대한 관리가 필요하고, 결제를 위한 내용이 필요하다.
(캐시는 프로젝트의 볼륨이 너무 커지는 듯 하여, 제외하였습니다.)

독자는 소설을 전부 즉시 결제로만 이용 가능하다.


웹소설
- 웹 소설 1화,2화(챕터)


작성해야 할 것

작가
    [v] 작가 등록
    [v] 웹 소설 등록
    [v] 웹 소설의 한 챕터를 등록한다 ( 금액과 함께 )
    [] 작가님의 출금 ( 결제한 것들에 대한 )
    [x] 웹 소설 R,U,D ? , 챕터에 대한 R,U,D

독자
    [v] 회원가입
    [v] 웹 소설의 챕터를 결제한다.
    [v] 웹 소설의 목록을 볼 수 있다.
    [v] 내가 특정 소설의 결제한 목록을 볼 수 있다.

접속
    http://localhost:8080/swagger-ui.html
    http://localhost:8080/h2

API 

curl http://11.150.131.98:8080/reader/webBook
curl --header "Content-Type: application/json" \
    --request POST \
    --data '{"name": "kim ki duck"}' \
    http://11.150.131.98:8080/reader/
