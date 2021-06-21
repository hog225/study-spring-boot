# carinfo 

test mockMVC
JPA, thymeleaf, metric, spring actuator, TDD 방법론(inventory) 
- From 패스트캠퍼스 스프링아카데미아 


1. [자료1](https://lifeonroom.com/study-lab/spring-boot-jpa-1/)
2. [자료2](https://lifeonroom.com/study-lab/spring-boot-jpa-2/)
3. [자료3](https://lifeonroom.com/study-lab/spring-boot-jpa-3/)


## 접속
1. http://localhost:8080
2. http://localhost:8080/h2


## 프로메테우스 
1. docker run -p 9090:9090 prom/prometheus
2. docker run -p 9090:9090 -v E:\GitProject\SpringBootStudy\car-info\src\main\resources\docker-server-properties\prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus

## 그라파나 
1. docker run -p 3000:3000 grafana/grafana
2. http://localhost:3000
3. 초기 접속정보 admin/admin

## seige http 부하 툴 
siege -c3 -t1m 192.xxx.xxx.xxx:8080/hello/30
siege -c2 -t5m 192.xxx.xxx.xxx:8080/hello/30

## sleuth
1. frontend http://localhost:8080/order/request back엔드로 전달
2. backend http://localhost:8080/order/100

### zipkin
1. curl -sSL https://zipkin.io/quickstart.sh | bash -s
1. java -jar zipkin.jar [github](https://github.com/openzipkin/zipkin)

## TDD


## Others
1. 로그인 횟수 정보, 특정 클릭 DB 가 맞을까 Metric이 맞을까 ?

