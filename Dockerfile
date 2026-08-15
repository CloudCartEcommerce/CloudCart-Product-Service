# Use Java 21 base image
FROM eclipse-temurin:21-jdk

# Working directory inside container
WORKDIR /app

# Copy Spring Boot JAR into container
COPY target/product-service-*.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Start application
ENTRYPOINT ["java", "-jar", "app.jar"]