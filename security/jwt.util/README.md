# JWT.util

JWT.util can generate a RSA keypair and save it as files namely key (private key) and key.pub (public key). The application can based on the RSA keypair create and validate JWT tokens. This can be helpfull for testing purposes or in case you need a test Identity provider.

## Build

Just run

````bash
mvn clean install
````

## Run

The following is an example of how the application can be used:

````bash
# Generate RSA key-pair
java -jar target/jwt.util-*-jar-with-dependencies.jar generateRSA

# Generate JWT token
MyJWT=$(java -jar target/jwt.util-*-jar-with-dependencies.jar generateJWT | cut -d ":" -f 4 | tr -d ' \t\n\r\f')
echo $MyJWT

# Validate token
java -jar target/jwt.util-*-jar-with-dependencies.jar validateJWT -t $MyJWT
````
