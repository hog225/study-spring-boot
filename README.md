# Spring Batch

## 들어가기 전에 ...
1. Spring Batch 의 도메인을 잘 이해 해야 한다.
2. chunk: 많은 데이터를 일정한 크기 단위로 데이터를 읽고 쓰는 프로세스
3. 오류제어
4. Batch 는 기본적으로 Single Thread 이다. 하지만 멀티로도 할 수 있다.
5. 리스너
6. 테스트

## 배치 패턴
1. read -> Process -> write
2. ETL 과 비슷 extract, transfort, load(적재)

## 구성
1. application
2. batch core -JobLauncher, job, Step, Flow / job을 설정하고 구성하는
   1. job은 반듯이 step을 가져야 한다. 
   2. step 안에서 비지니스 로직을 구현하는 단위 
   3. SimpleStepHandler 에서 step 이 실행됨
   4. Simple JobLauncher job 을 실행 시킴 
3. batch infrastructure - data르 처리하고 핸들링하는 queu, json 등등 

## 초기화 클래스 
1. BatchAutoConfiguration
2. SimpleBatchConfiguration
3. BatchConfigureConfiguration
   1. BasicBatchConfigurer
   2. JpaBatchConfigurer

## 스프링 배치 메타 데이터
* 스프링 배치의 실행 및 관리를 목적으로 여러 도메인들의 정보들을 저장 업데이트 조회 할 수 있는 스키마 제공 
- spring.batch.jdbc.initialize-schema 
  - ALWAYS: 운영에서는 위험 
  - EMBEDDED : 내부 DB에만 생성됨
  - NEVER: 수동 생성 
- Job 관련 테이블 
  - instance - job name, job key ==> key 
  - excution - job 의 실행정보가 저장 
  - excution_params - job param 저장 
  - excution_context - 상태정보 공유 데이터를 직렬화 해서 저장 
- step
  - excution - 생성 정보, 공유 데이터 
  - excution_context

![img.png](img/img.png)
- https://docs.spring.io/spring-batch/docs/current/reference/html/schema-appendix.html#metaDataSchema

### 진도 
- spring batch 도메인의 이해 