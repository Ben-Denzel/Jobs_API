FROM eclipse-temurin:17-jdk as builder
WORKDIR /app
# Copy just the files needed for Maven to work
COPY pom.xml .
COPY src src/
COPY mvnw .
COPY .mvn .mvn/
# Explicitly set permissions and build
RUN chmod +x mvnw && ./mvnw clean package

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]