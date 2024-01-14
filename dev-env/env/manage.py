import click
import subprocess


class DevEnvironmentManager:
    def __init__(self):
        pass

    @staticmethod
    @click.command()
    def start():
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


if __name__ == "__main__":
    dev_manager = DevEnvironmentManager()
    cli = click.Group()

    for attr_name in dir(dev_manager):
        attr = getattr(dev_manager, attr_name)

        if isinstance(attr, click.core.Command):
            cli.add_command(attr)

    cli()
