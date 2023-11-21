import psycopg2


def export_sql_data_to_file(connection, query, output_file_path):
    with connection.cursor() as cursor:
        with open(output_file_path, 'w') as output_file:
            cursor.copy_expert(query, output_file)


db_params = {
    'host': 'localhost',
    'database': 'e-government',
    'user': 'postgres',
    'password': '1234qwer',
}

# Establish a connection to the PostgreSQL database
connection = psycopg2.connect(**db_params)

# If necessary use other tables
db_table = 'users'
query = f'COPY {db_table} TO STDOUT WITH CSV HEADER'
output_file_path = '../../output.csv'
export_sql_data_to_file(connection, query, output_file_path)
