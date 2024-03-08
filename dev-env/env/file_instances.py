from scripts.files.file_migrations import FileSender

access_control_dockerfile = FileSender(
    src_path='../dockerfiles/access-control.Dockerfile',
    dest_dir_path='../../access-control'
)

authentication_dockerfile = FileSender(
    src_path='../dockerfiles/authentication.Dockerfile',
    dest_dir_path='../../authentication'
)

postgres_dockerfile = FileSender(
    src_path='../dockerfiles/postgres.Dockerfile',
    dest_dir_path='../../dev-env'
)

frontend_dockerfile = FileSender(
    src_path='../dockerfiles/frontend.Dockerfile',
    dest_dir_path='../../frontend'
)

main_dockerfile = FileSender(
    src_path='../dockerfiles/main.Dockerfile',
    dest_dir_path='../../main'
)

public_pem = FileSender(
    src_path='public.pem',
    dest_dir_path='../../authentication/src/main/resources/keys'
)

private_pem = FileSender(
    src_path='private.pem',
    dest_dir_path='../../authentication/src/main/resources/keys'
)


def transfer_files():
    # dockerfiles
    access_control_dockerfile.copy_and_send_file()
    authentication_dockerfile.copy_and_send_file()
    postgres_dockerfile.copy_and_send_file()
    frontend_dockerfile.copy_and_send_file()
    main_dockerfile.copy_and_send_file()

    # api key files
    public_pem.copy_and_send_file()
    private_pem.copy_and_send_file()
