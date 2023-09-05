# Stage 1: Build the review-service
FROM maven:3.8-openjdk-17-slim AS build-review-service
WORKDIR /app/review-service
COPY review-service/pom.xml ./pom.xml
COPY review-service/src ./src/
COPY review-service/root.crt ./
RUN mvn clean install

# Stage 2: Build the product-service
FROM maven:3.8-openjdk-17-slim AS build-product-service
WORKDIR /app/product-service
COPY product-service/pom.xml ./pom.xml
COPY product-service/src ./src/
RUN mvn clean install

# Stage 3: Create the final image
FROM openjdk:17-jdk-slim AS final
WORKDIR /parent

# Copy the compiled JAR files from the build stages
COPY --from=build-review-service /app/review-service/target/review-service.jar ./review-service.jar
COPY --from=build-product-service /app/product-service/target/product-service.jar ./product-service.jar

EXPOSE 8083
EXPOSE 8080

ENV SPRING_DATA_MONGODB_URI=mongodb+srv://user:egy4OdPi4Wyrm99k@cluster0.bgj8zvs.mongodb.net/product_db?retryWrites=true&w=majority
ENV POSTGRES_USER=review
ENV POSTGRES_PASSWORD=reviewpass
ENV POSTGRES_URL=jdbc:postgresql://postgres-db:5432/review-db

CMD ["sh", "-c", "java -jar product-service.jar & java -jar review-service.jar"]