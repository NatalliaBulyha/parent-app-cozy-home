FROM maven:3.8-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

FROM openjdk:17-jdk-slim AS final
WORKDIR /app
COPY --from=build /app/target/product-service.jar .
EXPOSE 8080
CMD ["java", "-jar", "product-service.jar"]