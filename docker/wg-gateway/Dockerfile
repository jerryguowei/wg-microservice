FROM openjdk:8-jdk-slim

VOLUME /tmp

ENV JAVA_OPTS=""

ENV WG_DOMAIN_NAME=""

ENV WG_CONFIG_URL=""

COPY /wg-gateway-1.0.jar /opt/

EXPOSE 8080

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /opt/wg-gateway-1.0.jar"]