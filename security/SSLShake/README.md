# SSLShake

SSL Shake is a simple Java program trying to make a SSL connection to a specific host and port. This can be helpfull when debugging problems related to SSL e.g. a missing root certificate in the truststore.

## Build

Just run

````bash
mvn clean install
````

## Run

The application can be run as follows:

````bash
java -jar target/SSLShake-*-jar-with-dependencies.jar google.com 443
````

You can also provide a specific truststore, in case needed, as follows:

````bash
java -Djavax.net.ssl.trustStore=myTrustStore -Djavax.net.ssl.trustStorePassword=demodemo -jar target/SSLShake-*-jar-with-dependencies.jar google.com 443
````

The application will return something similar to:

````log
16:44:57.355 INFO  - HTTP status line: HTTP/1.1 200 OK
16:44:57.357 INFO  - Connected Succesfully - HTTP
````
