# Use Java 17 JDK
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy Maven wrapper & pom for caching
COPY mvnw .
COPY pom.xml .
COPY .mvn .mvn

# Copy source code
COPY src src

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Copy jar
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
