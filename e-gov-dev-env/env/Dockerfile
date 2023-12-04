# Use the official PostgreSQL image as a base image
FROM postgres:latest

# Set environment variables
ENV POSTGRES_DB=${DB_NAME}
ENV POSTGRES_USER=${DB_USER}
ENV POSTGRES_PASSWORD=${DB_PASSWORD}

# Copy the initialization script to the /docker-entrypoint-initdb.d/ directory
COPY init.sql /docker-entrypoint-initdb.d/

# Expose the default PostgreSQL port
EXPOSE ${DB_HOST}