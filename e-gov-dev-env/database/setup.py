import psycopg2
from psycopg2 import sql
from psycopg2.extensions import ISOLATION_LEVEL_AUTOCOMMIT
import logging
import os

# Database connection parameters
DB_NAME = "e-gov-db"
DB_USER = "postgres"
DB_PASSWORD = os.getenv("DB_PASSWORD")  # Use environment variable for security

SCHEMA_NAME = "users"


def setup_database_connection():
    connection = psycopg2.connect(
        user=DB_USER,
        password=DB_PASSWORD,
        host="localhost",
        port=5432,
        dbname="postgres"
    )
    connection.set_isolation_level(ISOLATION_LEVEL_AUTOCOMMIT)
    return connection


def database_exists(conn, dbname):
    cursor = conn.cursor()
    cursor.execute(
        sql.SQL("SELECT 1 FROM pg_database WHERE datname = {}").format(sql.Identifier(dbname))
    )
    return cursor.fetchone() is not None


def create_database(conn, dbname, owner):
    cursor = conn.cursor()
    cursor.execute(
        sql.SQL("CREATE DATABASE {} OWNER {}").format(sql.Identifier(dbname), sql.Identifier(owner))
    )
    conn.commit()


def create_schema(conn, dbname, schema):
    cursor = conn.cursor()
    cursor.execute(
        sql.SQL("CREATE SCHEMA IF NOT EXISTS {}").format(sql.Identifier(schema))
    )
    conn.commit()


def main():
    logging.basicConfig(level=logging.INFO)
    connection = None  # Initialize connection outside the try block

    try:
        # Connect to the default postgres database
        connection = setup_database_connection()

        # Check if the database exists
        if not database_exists(connection, DB_NAME):
            # Create the database
            create_database(connection, DB_NAME, DB_USER)
            logging.info(f"Database '{DB_NAME}' created.")

        # Connect to the newly created or existing database
        connection = psycopg2.connect(
            user=DB_USER,
            password=DB_PASSWORD,
            host="localhost",
            port=5432,
            dbname=DB_NAME
        )

        # Create the schema
        create_schema(connection, DB_NAME, SCHEMA_NAME)
        logging.info(f"Schema '{SCHEMA_NAME}' created in '{DB_NAME}'.")

    except Exception as e:
        logging.error(f"An error occurred: {str(e)}")

    finally:
        # Close the connection
        if connection:
            connection.close()
            logging.info("Database connection closed.")


if __name__ == "__main__":
    main()
