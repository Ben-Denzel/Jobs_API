FROM eclipse-temurin:17-jdk as builder
WORKDIR /app
COPY pom.xml .
COPY src src/
COPY mvnw .
COPY .mvn .mvn/
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]