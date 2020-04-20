FROM openjdk:8-jdk-slim

VOLUME /tmp

ENV JAVA_OPTS=""

COPY /wg-eureka-1.0.jar /opt/

EXPOSE 8762

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /opt/wg-eureka-1.0.jar"]