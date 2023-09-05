FROM postgres:latest
ENV POSTGRES_USER=review
ENV POSTGRES_PASSWORD=reviewpass
ENV POSTGRES_DB=review-db

FROM cozyhomeimages/review-service:latest
ENV POSTGRES_USER=review
ENV POSTGRES_PASSWORD=reviewpass
ENV POSTGRES_URL=jdbc:postgresql://postgres-db:5432/review-db

FROM cozyhomeimages/product-service:latest