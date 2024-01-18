from api_keys import generate_key_pair
from file_migrations import FileSender

public_key, private_key = generate_key_pair()

public_key_file = FileSender(
    src_path='../../../authentication/src/main/resources/keys/public.pem',
    dest_dir_path=None
)

private_key_file = FileSender(
    src_path='../../../authentication/src/main/resources/keys/private.pem',
    dest_dir_path=None
)

public_key_file.write_text_on_file(
    key_content=public_key,
)

private_key_file.write_text_on_file(
    key_content=private_key,
)
