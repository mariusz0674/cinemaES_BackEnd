FROM postgres:alpine

ENV POSTGRES_PASSWORD=adminadmin

EXPOSE 5432

CMD ["postgres", "-c", "max_connections=500", "-c", "shared_buffers=256MB", "-c"]
#docker build -t cinema-image .
#docker run -p 5433:5432 cinema-image -U postgres
