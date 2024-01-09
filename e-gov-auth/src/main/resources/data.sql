ALTER ROLE postgres SET search_path TO 'postgres';
CREATE SCHEMA IF NOT EXISTS e_gov_auth;
ALTER ROLE postgres SET search_path TO 'e_gov_auth';

