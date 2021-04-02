FROM adoptopenjdk/openjdk11:x86_64-alpine-jdk-11.0.4_11

COPY build/libs/sn-0.0.1-SNAPSHOT.jar sn.jar

EXPOSE 8081

CMD java $JAVA_OPTS -jar sn.jar