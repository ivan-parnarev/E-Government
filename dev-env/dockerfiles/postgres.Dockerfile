# Use the official PostgreSQL image as a base image
FROM postgres:16

# Copy the initialization script to the /docker-entrypoint-initdb.d/ directory
COPY ../env/init.sql /docker-entrypoint-initdb.d/

# Expose the default PostgreSQL port
EXPOSE 5432