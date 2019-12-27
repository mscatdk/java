FROM maven:3.6.1-jdk-11-slim AS BUILD

WORKDIR /tmp

# Cache Dependencies
COPY pom.xml .
# Run a fake build as mvn dependency:resolve-plugins dependency:go-offline doesn't download all dependencies
RUN mvn clean install

# Build App
COPY src /tmp/src
RUN mvn clean install

FROM openjdk:8-jre-alpine
COPY --from=BUILD /tmp/target/*-jar-with-dependencies.jar /tmp/hallo.docker.jar
WORKDIR /tmp

EXPOSE 9090

CMD java -jar /tmp/hallo.docker.jar

HEALTHCHECK --interval=15s --timeout=3s CMD wget --quiet --tries=1 --spider http://localhost:9090/hello || exit 1
