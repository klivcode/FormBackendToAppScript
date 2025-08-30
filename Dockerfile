# Use Java 17 JDK
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy Maven wrapper & pom.xml for caching
COPY mvnw .
COPY pom.xml .
COPY .mvn .mvn

# Copy source code
COPY src src

# Make Maven wrapper executable
RUN chmod +x mvnw

# Build the Spring Boot app inside container
RUN ./mvnw clean package -DskipTests

# Copy the built jar from target
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
