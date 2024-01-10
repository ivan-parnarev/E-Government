# E-Government Access Control

## Introduction

This README provides instructions on how to start and run the Application. It's important to follow these steps to ensure the application runs correctly, particularly in regards to configuring PostgreSQL and initializing the database.

## Prerequisites

Before starting the application, ensure that you have PostgreSQL installed and running on your system. You also need to have the database tables created and filled with data.

## Configuration Steps
### 1. Configure PostgreSQL

To configure PostgreSQL for this application:
 ### Edit the postgresql.conf File:
### 1.1 Locate the `postgresql.conf`file
This file is typically found in the `PostgreSQL data` directory. Common locations include:

- Linux/Unix: `/var/lib/pgsql/data/postgresql.conf`

- Windows: `C:\Program Files\PostgreSQL\<version>\data\postgresql.conf`
- MacOS (if installed with Homebrew): `/usr/local/var/postgres/postgresql.conf`

Replace `<version>` with your PostgreSQL version. If you're unsure about the location, you can find it by running the SQL command `SHOW config_file;` in the **psql** command-line tool.

#### PSQL Command Line Tool and how to connect 
- **Open Terminal or Command Prompt**: Depending on your operating system, open a terminal (Linux/Mac) or command prompt (Windows).
- **Execute the psql Command**: The basic command to connect to a PostgreSQL database is:
- ```psql -d database_name -U user_name -h host_name -p port ``` Replace **database_name** with the name of your database, **user_name** with your PostgreSQL username, **host_name** with the hostname (use localhost if the database is on your local machine), and **port** with the port number **(default is 5432)**.
For example, to connect to a database named **e-government-access-control** as user postgres on your local machine, you would use: ```psql -d e-government-access-control -U postgres -h localhost -p 5432```
- If you want to switch to a different database you run `\c the_db_we_want_to_switch`

### 1.2 Edit the `postgresql.conf`file
- **Once the file is located you open it with text editor (VS Code is also an option)**
- **Add** you have to add configurations ``wal_level = logical`` ; ``max_replication_slots = ?``
  ``max_wal_senders = ?`` - **you must add a number at the place of the question mark** 5 is a great option = not too many but more than enough.
- **RESTART THE SERVER !** - very important step is to restart the PostgreSQL server or the changes might not be done
## 2. Start the Main Service

Run the main service of the application to create the publication. This is a crucial step and should be done before starting the Access Control service. **For successful creation of the publication the schema
must have the tables CREATED (not to be on create-drop mode)**
## 3. Run the Access Control Service
Once the main service is up and running, and the publication is created, you can start the Access Control service. Ensure that the server is running with already created and populated tables.
## Additional Information

- If you want to be sure that everything is created you might run a couple of commands in the **psql** client. Switch to the original database.
- - ``SELECT * FROM pg_replication_slots;`` - selects all replication slots - for the current situation you must have a slot named `e_government_subscription`
- - ``SELECT * FROM pg_stat_subscription;``selects all subscriptions - for the current situation you must have a subscription named `e_government_subscription`
- - ``SELECT * FROM pg_publication;`` - selects all publications - for the current situation you must have a publication named `e_government_campaigns_publication`