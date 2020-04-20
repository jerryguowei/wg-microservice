FROM openjdk:8-jdk-slim

VOLUME /tmp

ENV JAVA_OPTS=""

COPY /wg-usercenter-1.0.jar /opt/

EXPOSE 8200

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /opt/wg-usercenter-1.0.jar"]