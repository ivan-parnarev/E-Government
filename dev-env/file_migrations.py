import shutil
import os


def copy_and_send_file(src_path, dest_directory):
    if not os.path.exists(src_path):
        raise Exception(f"Error: Source file '{src_path}' does not exist.")

    dest_path = os.path.join(dest_directory, os.path.basename(src_path))

    try:
        shutil.copy2(src_path, dest_path)
        print(f"File '{src_path}' copied to '{dest_path}' successfully.")
    except Exception as e:
        print(f"Error copying file: {e}")


source_file_path = 'resources/application.yml'
destination_directory_path = '../main/src/main/resource'

copy_and_send_file(source_file_path, destination_directory_path)
