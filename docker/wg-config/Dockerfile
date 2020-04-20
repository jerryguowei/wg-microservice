FROM openjdk:8-jdk-slim

VOLUME /tmp

ENV JAVA_OPTS=""

COPY /wg-config-1.0.jar /opt/

EXPOSE 8888

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /opt/wg-config-1.0.jar"]