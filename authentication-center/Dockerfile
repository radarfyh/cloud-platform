FROM java:8-jre
MAINTAINER Edison Feng <edison.feng@fulaan.com>

ADD ./target/authentication-center.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/authentication-center.jar"]

EXPOSE 5000