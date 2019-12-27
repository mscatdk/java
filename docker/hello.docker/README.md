# hello.docker

Show one option for packaging a simple Java application into a Docker container using a multi-stage docker build caching maven dependencies and minimizing the final container size.

The Makefile provides an easy way to get started:

| command | Description |
| ------- |------------ |
| make dev | Build the application and run it locally (No use of Docker) |
| make build | Build a Docker image |
| make run | Run the docker image created using the above command |
