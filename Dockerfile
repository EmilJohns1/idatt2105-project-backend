# Use a base image with Java and Maven installed
FROM maven:latest AS build

# Set the working directory
WORKDIR /app

# Copy the source code to the container
COPY . .

#Set environment variable for keystore
RUN export KEYSTORE_PASSWORD='changeit'

# Build the application, this needs to skip spotless check
RUN mvn spotless:apply
RUN mvn clean package -DskipTests

# Install mkcert and openssl
RUN apt-get update 
RUN apt install -y libnss3-tools
RUN apt-get install -y openssl
#RUN curl -JLO "https://dl.filippo.io/mkcert/latest?for=linux/amd64"
#RUN chmod +x mkcert-v*-linux-amd64
#RUN cp mkcert-v*-linux-amd64 /usr/local/bin/mkcert
#RUN mkcert -install
# Copy the compiled JAR file from the build stage
#COPY --from=build /app/target/my-application.jar /app/backend-1.1.jar

# Generate the .p12 file with the password "changeit"


# Set the working directory
#WORKDIR /app

# Expose port 8443
#EXPOSE 8443

# Run the Spring Boot application
#CMD ["java", "-jar", "backend-1.1.jar"]