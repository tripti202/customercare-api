FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy Maven files
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# 🔥 THIS LINE FIXES THE ERROR
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build application
RUN ./mvnw clean package -DskipTests

# Run app
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/customercare-api-0.0.1-SNAPSHOT.jar"]
