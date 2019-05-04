# Flyway

Flyway makes it easy to track and apply DB changes.

## MySQL example

Properties can also be specified in pom.xml or in a properties file.

````bash
# Apply all migrations
mvn flyway:migrate -Dflyway.password=[password] -Dflyway.user=[username] -Dflyway.url=jdbc:mysql://[hostname]/[database]

# Apply all migrations up until a specific version
mvn flyway:migrate -Dflyway.password=[password] -Dflyway.user=[username] -Dflyway.url=jdbc:mysql://[hostname]/[database] -Dflyway.target=[version]

# Delete all DB objects
mvn flyway:clean -Dflyway.password=[password] -Dflyway.user=[username] -Dflyway.url=jdbc:mysql://[hostname]/[database]
````