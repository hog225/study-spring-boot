# carinfo 

test mockMVC
JPA, thymeleaf, metric, spring actuator 
- From 패스트캠퍼스 스프링아카데미아 


1. [자료1](https://lifeonroom.com/study-lab/spring-boot-jpa-1/)
2. [자료2](https://lifeonroom.com/study-lab/spring-boot-jpa-2/)
3. [자료3](https://lifeonroom.com/study-lab/spring-boot-jpa-3/)


## 접속
1. http://localhost:8080
2. http://localhost:8080/h2


## 프로메테우스 
docker run -p 9090:9090 prom/prometheus
docker run -p 9090:9090 -v E:\GitProject\SpringBootStudy\car-info\src\main\resources\docker-server-properties\prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus

## seige http 부하 툴 
siege -c3 -t1m 192.xxx.xxx.xxx:8080/hello/30