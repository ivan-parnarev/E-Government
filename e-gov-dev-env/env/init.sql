CREATE DATABASE "e-gov-db";

-- Switch to the newly created database
\c e-gov-db;

-- Create a table if it doesn't exist
CREATE TABLE IF NOT EXISTS test (
    id serial PRIMARY KEY,
    name VARCHAR (100) NOT NULL
);
