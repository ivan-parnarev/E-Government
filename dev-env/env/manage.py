import click
import subprocess
from api.api_keys import APIKeys
from file_instances import transfer_files
from cryptography.hazmat.primitives.asymmetric import ec


class DevEnvironmentManager:
    def __init__(self):
        pass

    @staticmethod
    @click.command()
    def start():
        get_api_keys()
        transfer_files()
        subprocess.run(["docker-compose", "up", "-d"])
        click.echo("Starting to develop containers...")

    @staticmethod
    @click.command()
    def end():
        subprocess.run(["docker-compose", "down"])
        click.echo("Deleting running containers...")

    @staticmethod
    @click.command()
    def stop():
        subprocess.run(["docker-compose", "stop"])
        click.echo("Stopping running containers...")


def get_api_keys():
    keys = APIKeys()

    keys.private_key = ec.generate_private_key(ec.SECP256R1())
    keys.public_key = keys.private_key.public_key()

    public_pem, private_pem = keys.generate_key_pair()
    keys.create_pem_files_with_keys(public_pem, private_pem)


def main():
    dev_manager = DevEnvironmentManager()
    cli = click.Group()

    for attr_name in dir(dev_manager):
        attr = getattr(dev_manager, attr_name)

        if isinstance(attr, click.core.Command):
            cli.add_command(attr)

    cli()


if __name__ == "__main__":
    main()
