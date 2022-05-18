# MQ TEST


- https://www.baeldung.com/spring-cloud-stream 
- https://docs.spring.io/spring-cloud-stream/docs/3.2.0-SNAPSHOT/reference/html/spring-cloud-stream.html#spring-cloud-stream-reference - SPRING cloud stream ref


1. producer - amqp
2. comsumer -amqp
3. stream-consumer - spring cloud stream
4. stream-producer - spring cloud stream



## run
1. docker-compose up - running on ubuntu/ lilocalhost
2. run comsumer and producer

## API
1. http://localhost:8001/test/queue/pub-string
2. http://localhost:8001/test/queue/pub-json
3. http://localhost:8001/test/queue/pub-string-simple-message-queue
3. http://localhost:8003/index/1
3. http://localhost:8003/index1/2

## More
- 3.1 부터 @EnableBinding, @StreamLinstener 가 Deprecated 되었다...

## kafka 
1. 토픽 과 파티션
- 토픽은 프로듀스 컨슘하는 단위 
- 파티션은 다수 존재 가능 파티션이 다수 존재 한다면 데이터는 round robin 방식으로 파티션에 들어감
- 파티션을 줄일 수 없음 
2. broker, ISR, replication
- 브로커는 카푸카가 도는 서버의 단위 
- replication 은 파티션의 복제품, 브로커=3, partition=1, replication=3 하개되면 브로커 각각에 follow partition 이 생김
- producer 는 ack 를 통해 고가용성 통제를 함
    - 0: 응답 안받음
    - 1: leader partition 에 대한 응답만 받음 
    - all: 모든 replication에 데이터가 써진 경우에만 응답을 성공으로 받음

3. 파티셔너
- 프로듀서의 영역 
- 카프카의 어떤 파티션에 데이터를 저장할지에 대한 설정 
- 기본 sticky..Patitioner가 기본 파티셔너임 
- 키가 있는 경우 없는 경우 서로 다른 동작을 함 
- custom partitioner 가능 
4. 컨슈머 랙 
- 프로듀서 오프셋, 컨슈머 오프셋 간에 차이
- 오프셋이란 데이터작업을 하고 있는 인덱스 예를 들면 컨슈머 오프셋은 컨슈머가 데이터를 읽은 인덱스 
- 랙은 여러개 존재할 수 있다. 파티션이 여렇개일 수 있음으로 
5. burrow
- 컨슈머 랙 모니터링을 도와줌 
