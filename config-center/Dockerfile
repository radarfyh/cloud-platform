FROM java:8-jre
MAINTAINER Edison Feng <edison.feng@fulaan.com>

ADD ./target/config-center.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/config-center.jar"]

HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8888/actuator/health || exit 1

EXPOSE 8888