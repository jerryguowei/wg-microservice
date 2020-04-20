FROM openjdk:8-jdk-slim

VOLUME /tmp

ENV JAVA_OPTS=""

COPY /wg-uaa-1.0.jar /opt/

EXPOSE 8100

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /opt/wg-uaa-1.0.jar"]