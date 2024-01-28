# Use the official PostgreSQL image as a base image
FROM postgres:16

# Add your custom configuration
RUN echo "max_level = logical" >> /var/lib/postgresql/data/postgresql.conf && \
    echo "max_replication_slots = 5" >> /var/lib/postgresql/data/postgresql.conf && \
    echo "max_wal_senders = 5" >> /var/lib/postgresql/data/postgresql.conf \

# Copy the initialization script to the /docker-entrypoint-initdb.d/ directory
COPY init.sql /docker-entrypoint-initdb.d/

# Expose the default PostgreSQL port
EXPOSE 5432