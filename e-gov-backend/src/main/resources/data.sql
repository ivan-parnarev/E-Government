ALTER ROLE "postgres" SET search_path TO "e-gov-db";
CREATE SCHEMA IF NOT EXISTS e_gov_db;
ALTER ROLE "postgres" SET search_path TO "e_gov_db";