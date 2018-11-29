FROM java:8-jre
MAINTAINER Edison Feng <edison.feng@fulaan.com>

ADD ./target/hystrix-dashboard.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/hystrix-dashboard.jar"]

EXPOSE 9000