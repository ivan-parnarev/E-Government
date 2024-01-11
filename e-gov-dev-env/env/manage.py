import argparse
import subprocess


class DevEnvironmentManager:
    def __init__(self):
        pass

    @staticmethod
    def start_frontend():
        subprocess.run(["docker-compose", "up", "-d", "spring-boot", "postgres"])

    @staticmethod
    def start_backend():
        subprocess.run(["docker-compose", "up", "-d", "react-app", "postgres"])

    @staticmethod
    def start_manager():
        subprocess.run(["docker-compose", "up", "-d"])


def main():
    dev_manager = DevEnvironmentManager()

    parser = argparse.ArgumentParser(description='Dev Environment Configuration CLI')
    parser.add_argument(
        'command',
        choices=[
            'start_frontend',
            'start_backend',
            'start_manager'
        ],
        help='Command to perform',
    )

    parser.add_argument(
        '--num_people',
        type=int,
        help='Number of people to generate',
        default=50,
    )

    args = parser.parse_args()

    if args.command == 'start_frontend':
        dev_manager.start_frontend()
    elif args.command == 'start_backend':
        dev_manager.start_backend()
    elif args.command == 'start_manager':
        dev_manager.start_manager()


if __name__ == '__main__':
    main()
