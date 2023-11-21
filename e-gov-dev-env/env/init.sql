-- Check if the database exists
DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'e-gov-db') THEN
        -- Create the database
        CREATE DATABASE "e-gov-db";
    END IF;
END $$;

-- Switch to the newly created or existing database
\c "e-gov-db";

-- Create a table if it doesn't exist
CREATE TABLE IF NOT EXISTS test (
    id serial PRIMARY KEY,
    name VARCHAR (100) NOT NULL
);

