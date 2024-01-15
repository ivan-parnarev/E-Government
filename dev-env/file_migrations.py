import shutil
import os


class FileSender:
    def __init__(self, src_path, dest_dir_path):
        self.src_path = src_path
        self.dest_dir_path = dest_dir_path

    def copy_and_send_file(self):
        if not os.path.exists(self.src_path):
            raise Exception(f"Error: Source file '{self.src_path}' does not exist.")

        dest_path = os.path.join(
            self.dest_dir_path,
            os.path.basename(self.src_path)
        )

        try:
            shutil.copy2(self.src_path, dest_path)
            print(f"File '{self.src_path}' copied to '{dest_path}' successfully.")
        except Exception as e:
            print(f"Error copying file: {e}")


sender = FileSender(
    src_path='resources/application.yml',
    dest_dir_path='../main/src/main/resources'
)

sender.copy_and_send_file()
