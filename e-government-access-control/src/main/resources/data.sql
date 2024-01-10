ALTER ROLE postgres SET search_path TO 'e-government-access-control';
CREATE SCHEMA IF NOT EXISTS e_gov_db;
ALTER ROLE postgres SET search_path TO e_gov_db;