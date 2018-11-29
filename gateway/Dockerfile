FROM java:8-jre
MAINTAINER Edison Feng <edison.feng@fulaan.com>

ADD ./target/gateway.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/gateway.jar"]

EXPOSE 4000