from file_migrations import FileSender

access_control_yml = FileSender(
    src_path='../../resources/access-control/application.yml',
    dest_dir_path='../../../access-control/src/main/resources'
)

authentication_yml = FileSender(
    src_path='../../resources/authentication/application.yml',
    dest_dir_path='../../../authentication/src/main/resources'
)

main_yml = FileSender(
    src_path='../../resources/main/application.yml',
    dest_dir_path='../../../main/src/main/resources'
)

access_control_dockerfile = FileSender(
    src_path='../../dockerfiles/access-control.Dockerfile',
    dest_dir_path='../../../access-control'
)

authentication_dockerfile = FileSender(
    src_path='../../dockerfiles/authentication.Dockerfile',
    dest_dir_path='../../../authentication'
)

postgres_dockerfile = FileSender(
    src_path='../../dockerfiles/postgres.Dockerfile',
    dest_dir_path='../..'
)

frontend_dockerfile = FileSender(
    src_path='../../dockerfiles/frontend.Dockerfile',
    dest_dir_path='../../../frontend'
)

main_dockerfile = FileSender(
    src_path='../../dockerfiles/main.Dockerfile',
    dest_dir_path='../../../main'
)

access_control_sql = FileSender(
    src_path='../../resources/access-control/data.sql',
    dest_dir_path='../../../access-control/src/main/resources'
)

authentication_sql = FileSender(
    src_path='../../resources/authentication/data.sql',
    dest_dir_path='../../../authentication/src/main/resources'
)

main_sql = FileSender(
    src_path='../../resources/main/data.sql',
    dest_dir_path='../../../main/src/main/resources'
)

public_pem = FileSender(
    src_path='../api/public.pem',
    dest_dir_path='../../../authentication/src/main/resources/keys'
)

private_pem = FileSender(
    src_path='../api/private.pem',
    dest_dir_path='../../../authentication/src/main/resources/keys'
)


def main():
    # application.yml
    access_control_yml.copy_and_send_file()
    authentication_yml.copy_and_send_file()
    main_yml.copy_and_send_file()

    # dockerfiles
    access_control_dockerfile.copy_and_send_file()
    authentication_dockerfile.copy_and_send_file()
    postgres_dockerfile.copy_and_send_file()
    frontend_dockerfile.copy_and_send_file()
    main_dockerfile.copy_and_send_file()

    # data.sql
    access_control_sql.copy_and_send_file()
    authentication_sql.copy_and_send_file()
    main_sql.copy_and_send_file()

    # api key files
    public_pem.copy_and_send_file()
    private_pem.copy_and_send_file()


if __name__ == '__main__':
    main()
