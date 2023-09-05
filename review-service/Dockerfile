FROM maven:3.8-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

FROM openjdk:17-jdk-slim AS final
WORKDIR /app
COPY --from=build /app/target/review-service.jar .
EXPOSE 8083
ENTRYPOINT ["java","-jar","review-service.jar"]
