FROM java:8-jre
MAINTAINER Edison Feng <edison.feng@fulaan.com>

ADD ./target/registration-center.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/registration-center.jar"]

EXPOSE 8761