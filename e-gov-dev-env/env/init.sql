\c e-gov-db;

-- Create a table if it doesn't exist
CREATE TABLE IF NOT EXISTS users (
    id serial PRIMARY KEY,
    name VARCHAR (100) NOT NULL
);

INSERT INTO users (name) VALUES ('Yana');
INSERT INTO users (name) VALUES ('Nedko');
INSERT INTO users (name) VALUES ('Koko');
INSERT INTO users (name) VALUES ('Bogi');
INSERT INTO users (name) VALUES ('Ivan');