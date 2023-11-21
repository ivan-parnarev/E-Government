import psycopg2


def import_csv_data_to_database(connection, table_name, input_file_path, delimiter=',', header=True):
    with connection.cursor() as cursor:
        with open(input_file_path, 'r') as input_file:
            if header:
                cursor.copy_expert(
                    f"COPY {table_name} FROM STDIN WITH CSV HEADER DELIMITER '{delimiter}'",
                    input_file
                )
            else:
                cursor.copy_expert(
                    f"COPY {table_name} FROM STDIN WITH CSV DELIMITER '{delimiter}'",
                    input_file
                )
        connection.commit()


db_params = {
    'host': 'localhost',
    'database': 'e-government',
    'user': 'postgres',
    'password': '1234qwer',
}

# Establish a connection to the PostgreSQL database
connection = psycopg2.connect(**db_params)

table_name = "users"  # Change this to your target table name
input_file_path = "../../output.csv"
import_csv_data_to_database(connection, table_name, input_file_path)
