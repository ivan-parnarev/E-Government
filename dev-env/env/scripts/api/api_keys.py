from cryptography.hazmat.primitives import serialization


class APIKeys:
    def __init__(self, ):
        self.__public_key = None
        self.__private_key = None

    def generate_key_pair(self):
        public_key_pem = self.__public_key.public_bytes(
            encoding=serialization.Encoding.PEM,
            format=serialization.PublicFormat.SubjectPublicKeyInfo
        )

        private_key_pem = self.__private_key.private_bytes(
            encoding=serialization.Encoding.PEM,
            format=serialization.PrivateFormat.PKCS8,
            encryption_algorithm=serialization.NoEncryption()
        )

        return public_key_pem, private_key_pem

    @staticmethod
    def create_pem_files_with_keys(public_key_file, private_key_file):
        with open('public.pem', 'wb') as file:
            file.write(public_key_file)

        with open('private.pem', 'wb') as file:
            file.write(private_key_file)

        return None

    @property
    def public_key(self):
        return self.__public_key

    @public_key.setter
    def public_key(self, value):
        if value is None:
            raise ValueError('Public key must not be a None value')

        self.__public_key = value

    @property
    def private_key(self):
        return self.__private_key

    @private_key.setter
    def private_key(self, value):
        if value is None:
            raise ValueError('Private key must not be a None value')

        self.__private_key = value
