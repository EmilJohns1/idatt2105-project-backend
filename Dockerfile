FROM maven:3.8.3-jdk-17 AS builder

# Copy the pom.xml file
COPY pom.xml /app/

# Copy the source code
COPY src /app/src

# Change working directory to /app
WORKDIR /app

# Package the application
RUN mvn -B package

# Start the MySQL container (if needed)
# RUN docker-compose -f /app/docker-compose.yml up -d mysql

# Create the final image
FROM openjdk:17

# Copy the JAR file
COPY --from=builder /app/target/your-application.jar /app/

# Change working directory to /app
WORKDIR /app

# Command to run the application
CMD ["java", "-jar", "your-application.jar"]
