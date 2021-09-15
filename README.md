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