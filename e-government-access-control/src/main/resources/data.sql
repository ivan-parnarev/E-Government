ALTER ROLE postgres SET search_path TO 'e-government-access-control';
CREATE SCHEMA IF NOT EXISTS e_gov_db;
ALTER ROLE postgres SET search_path TO e_gov_db;

CREATE SUBSCRIPTION e_government_subscription
            CONNECTION 'host=localhost port=5432 dbname=e-gov-db user=postgres password=Yana13042308'
            PUBLICATION e_government_campaigns_publication
            WITH (synchronous_commit = 'off', copy_data = true, enabled = true);

-- DO '
-- BEGIN
--     IF NOT EXISTS (SELECT 1 FROM pg_subscription WHERE subname = ''my_subscription'') THEN
--         CREATE SUBSCRIPTION my_subscription
--             CONNECTION ''host=localhost port=5432 dbname=e-gov-db user=postgres password=Yana13042308''
--             PUBLICATION e_gov_campaigns_publication
--             WITH (synchronous_commit = ''off'', copy_data = true, enabled = true);
--         END IF;
-- END
-- ';