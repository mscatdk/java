FROM maven:3.6.1-jdk-11-slim AS BUILD
COPY pom.xml /tmp
COPY src /tmp/src

WORKDIR /tmp

RUN mvn clean install

FROM openjdk:8-jdk-stretch
COPY --from=BUILD /tmp/target/*-jar-with-dependencies.jar /tmp/hallo.docker.jar
WORKDIR /tmp

EXPOSE 9090

CMD java -jar /tmp/hallo.docker.jar

HEALTHCHECK --interval=15s --timeout=3s CMD wget --quiet --tries=1 --spider http://localhost:9090/hello || exit 1