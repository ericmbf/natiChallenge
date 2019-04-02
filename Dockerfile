FROM openjdk:8u151-jre-alpine3.7

LABEL maintainer="Eric Costa Hall"

ENV JAVA_OPTS=""

VOLUME /tmp

ADD ./target/*.jar /var/www/app.jar

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /var/www/app.jar