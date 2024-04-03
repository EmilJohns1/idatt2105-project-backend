# Use a base image with Java and Maven installed
FROM maven:latest AS build

# Set the working directory
WORKDIR /app

# Copy the source code to the container
COPY . .

#Set environment variable for keystore
ARG password
ENV KEYSTORE_PASSWORD=${password}

# Set the application.properties to the docker-properties
WORKDIR /app/src/main/resources
RUN rm application.properties
RUN mv docker-properties application.properties

WORKDIR /app

# Install mkcert and openssl
RUN apt-get update 
RUN apt install -y libnss3-tools
RUN apt-get install -y openssl
RUN curl -JLO "https://dl.filippo.io/mkcert/latest?for=linux/amd64"
RUN chmod +x mkcert-v*-linux-amd64
RUN cp mkcert-v*-linux-amd64 /usr/local/bin/mkcert
RUN mkcert -install

WORKDIR /app/src/main/resources/keystore

#Create certificate and key files
RUN mkcert -key-file key.pem -cert-file cert.pem localhost 127.0.0.1 ::1

# Create a keystore. 
RUN openssl pkcs12 -export -out keystore.p12 -inkey key.pem -in cert.pem -passout env:KEYSTORE_PASSWORD

WORKDIR /app
# Build the application, this needs to skip spotless check
RUN mvn spotless:apply
RUN mvn clean package -DskipTests

# Expose port 8443
EXPOSE 8443

# Finds the first jar file in the target directory and runs it. This will always be the spring boot application
CMD java -jar $(find /app/target -name '*.jar' | head -n 1)