# Spring-Batch
1. IDE IJ 사용
2. 해당 내용 참고 - https://github.com/spring-academia/1st-open/tree/main/Spring%20Batch%EB%A5%BC%20%ED%99%9C%EC%9A%A9%ED%95%9C%20%EB%8D%B0%EC%9D%B4%ED%84%B0%20%EC%9D%BC%EA%B4%84%20%EC%B2%98%EB%A6%AC

## JOB
- [JobScheduler.java](src%2Fmain%2Fjava%2Forg%2Fyg%2Fpractivce%2Fspringbatch%2Fapplication%2Fjob%2FJobScheduler.java) 에서 실행 가능 
### meterJob

### meterTaskletJob

### createArticleJob


### DB 로그 
```bash
SET global general_log = on;
SET GLOBAL slow_query_log = 'ON';
SET global general_log_file='/var/log/mysql/mysql.log';
SET global log_output = 'file';
```