import shutil
import os


class FileSender:
    def __init__(self, src_path, dest_dir_path):
        self.src_path = src_path
        self.dest_dir_path = dest_dir_path

    def copy_and_send_file(self):
        if not os.path.exists(self.src_path):
            raise Exception(f"Error: Source file '{self.src_path}' does not exist.")

        dest_path = get_dest_path(
            self.dest_dir_path,
            self.src_path
        )

        try:
            shutil.copy2(self.src_path, dest_path)
            print(f"File '{self.src_path}' copied to '{dest_path}' successfully.")
        except Exception as e:
            print(f"Error copying file: {e}")


def get_dest_path(dest_dir_path, src_path):
    return os.path.join(
        dest_dir_path,
        os.path.basename(src_path)
    )


main_app_yml = FileSender(
    src_path='resources/main.application.yml',
    dest_dir_path='../main/src/main/resources'
)

access_control_app_yml = FileSender(
    src_path='resources/main.application.yml',
    dest_dir_path='../access-control/src/main/resources'
)

access_control_dockerfile = FileSender(
    src_path='dockerfiles/access-control.Dockerfile',
    dest_dir_path='../access-control'
)

authentication_dockerfile = FileSender(
    src_path='dockerfiles/authentication.Dockerfile',
    dest_dir_path='../authentication'
)

postgres_dockerfile = FileSender(
    src_path='dockerfiles/postgres.Dockerfile',
    dest_dir_path='../dev-env'
)

frontend_dockerfile = FileSender(
    src_path='dockerfiles/frontend.Dockerfile',
    dest_dir_path='../frontend'
)

main_dockerfile = FileSender(
    src_path='dockerfiles/main.Dockerfile',
    dest_dir_path='../main'
)

main_app_yml.copy_and_send_file()
access_control_app_yml.copy_and_send_file()

access_control_dockerfile.copy_and_send_file()
authentication_dockerfile.copy_and_send_file()
postgres_dockerfile.copy_and_send_file()
frontend_dockerfile.copy_and_send_file()
main_dockerfile.copy_and_send_file()
