import psycopg2


class Database:
    def __init__(self, connection, query, output_file_path, input_file_path, table_name):
        self.connection = connection
        self.query = query
        self.output_file_path = output_file_path
        self.input_file_path = input_file_path
        self.table_name = table_name

    def export_sql_data_to_file(self):
        with self.connection.cursor() as cursor:
            with open(self.output_file_path, 'w') as output_file:
                cursor.copy_expert(self.query, output_file)

    def import_csv_data_to_database(self, delimiter=',', header=True):
        with self.connection.cursor() as cursor:
            with open(self.input_file_path, 'r') as input_file:
                if header:
                    cursor.copy_expert(
                        f"COPY {self.table_name} FROM STDIN WITH CSV HEADER DELIMITER '{delimiter}'",
                        input_file
                    )
                else:
                    cursor.copy_expert(
                        f"COPY {self.table_name} FROM STDIN WITH CSV DELIMITER '{delimiter}'",
                        input_file
                    )
            self.connection.commit()


db_params = {
    'host': 'localhost',
    'database': 'e-government',
    'user': 'postgres',
    'password': '1234qwer',
}

db = Database(
    connection=psycopg2.connect(**db_params),
    query=f'COPY users TO STDOUT WITH CSV HEADER',
    output_file_path='../../output.csv',
    input_file_path='../../input.csv',
    table_name='users',
)

db.export_sql_data_to_file()
