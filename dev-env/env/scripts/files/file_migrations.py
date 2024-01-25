import shutil
import os


class FileSender:
    def __init__(self, src_path, dest_dir_path):
        self.src_path = src_path
        self.dest_dir_path = dest_dir_path

    def copy_and_send_file(self):
        if not os.path.exists(self.src_path):
            raise Exception(f"Error: Source file '{self.src_path}' does not exist.")

        if not os.path.exists(self.dest_dir_path):
            os.makedirs(self.dest_dir_path)

        dest_path = get_dest_path(
            self.dest_dir_path,
            self.src_path
        )

        try:
            shutil.copy2(self.src_path, dest_path)
            print(f"File '{self.src_path}' copied to '{dest_path}' successfully.")
        except Exception as e:
            print(f"Error copying file: {e}")

    def write_text_on_file(self, key_content):
        with open(self.src_path, 'w') as file:
            file.write(key_content)


def get_dest_path(dest_dir_path, src_path):
    return os.path.join(
        dest_dir_path,
        os.path.basename(src_path)
    )
