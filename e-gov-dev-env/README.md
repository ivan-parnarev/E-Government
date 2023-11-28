# Running Docker Compose Configuration
This repository utilizes Docker Compose to streamline the deployment process. Follow the steps below to set up the environment and launch the application.
1. Start the Docker Engine
Ensure that the Docker Engine is installed and running on your machine. If you haven't installed Docker yet, you can download it from the official Docker website.
2. Navigate to the env Folder
Open your terminal or command prompt and navigate to the env folder within the project directory using the following command:
bashCopy code
cd ./env/ 
3. Build the Docker Configuration
Run the following command to build and start the Docker containers defined in the docker-compose.yml configuration file. The -d flag runs the containers in the background.
bashCopy code
docker-compose up -d 
This command will pull the necessary images, create containers, and set up the defined services.
4. Connect to the PostgreSQL Server
Once the Docker containers are up and running, you can connect to the PostgreSQL server using your preferred database management tool or by accessing it through the command line. If you are using a tool like psql, the connection details (such as host, port, username, and password) are specified in the docker-compose.yml file.

