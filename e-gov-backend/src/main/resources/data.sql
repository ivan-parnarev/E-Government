ALTER ROLE postgres SET search_path TO 'e-gov-db';
CREATE SCHEMA IF NOT EXISTS e_gov_db;
ALTER ROLE postgres SET search_path TO e_gov_db;

-- DO '
--     BEGIN
--         IF NOT EXISTS (SELECT 1 FROM pg_publication WHERE pubname = ''e_government_campaigns_publication'') THEN
--             CREATE PUBLICATION e_government_campaigns_publication FOR TABLE campaigns, users, roles, users_roles;
--         END IF;
--     END
-- ';