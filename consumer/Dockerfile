FROM openjdk:12.0.2

EXPOSE 8002

ADD ./build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
