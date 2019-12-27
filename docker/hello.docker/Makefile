IMAGE=mscatdk/hello.docker
TAG=latest

.PHONY: dev, build, run
default: dev

dev:
	mvn clean install
	java -jar target/*-jar-with-dependencies.jar

build:
	docker build . --tag ${IMAGE}:${TAG}

run:
	docker run -d --rm -p 9090:9090 ${IMAGE}:${TAG}
