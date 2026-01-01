# Use official Java 17 image
FROM eclipse-temurin:17-jdk-alpine

# App directory inside container
WORKDIR /app

# Copy Maven build
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8080

# Run app
CMD ["java", "-jar", "target/customercare-api-0.0.1-SNAPSHOT.jar"]
