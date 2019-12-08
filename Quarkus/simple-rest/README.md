# Simple-rest

## Build using local environment

````Bash
# Install Maven
wget http://mirrors.dotsrc.org/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
tar xf apache-maven-3.6.3-bin.tar.gz -C /opt
ln -s /opt/apache-maven-3.6.3 /opt/maven
update-alternatives --install /usr/bin/mvn mvn /opt/maven/bin/mvn 100

# Install c build environment
apt-get install build-essential libz-dev zlib1g-dev

# Install graalvm
wget https://github.com/oracle/graal/releases/download/vm-19.2.1/graalvm-ce-linux-amd64-19.2.1.tar.gz
tar xf graalvm-ce-linux-amd64-19.2.1.tar.gz -C /opt
ln -s /opt/graalvm-ce-19.2.1 /opt/graalvm

# Setup build environment
export GRAALVM_HOME=/opt/graalvm
export JAVA_HOME=${GRAALVM_HOME}
${GRAALVM_HOME}/bin/gu install native-image

# Build Java App
mvn clean install

# Build Native App
mvn package -Pnative
````

## Build using Docker

````Bash
# Install Docker
# Install maven + JDK

# Build native app inside container
mvn package -Pnative -Dquarkus.native.container-build=true
````
