# Use the official PostgreSQL image as a base image
FROM postgres:latest

# Set environment variables
ENV POSTGRES_DB=e-gov-db
ENV POSTGRES_USER=postgres-user
ENV POSTGRES_PASSWORD=1234qwer

# Copy the initialization script to the /docker-entrypoint-initdb.d/ directory
COPY init.sql /docker-entrypoint-initdb.d/

# Expose the default PostgreSQL port
EXPOSE 5432