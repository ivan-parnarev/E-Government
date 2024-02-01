# Kafka-Consumer

## Description

Key features and functionalities include:

- **Dynamic Listener Management**: The service has the ability to dynamically
create Kafka listeners for specific topics. This allows it to be flexible and responsive to changes in data streaming requirements without the need for manual intervention or redeployment.

- **Listener Lifecycle Control**: Beyond creation, the service provides endpoints to start and stop these listeners as needed. This on-the-fly control facilitates efficient resource management and enables the service to adapt to varying workloads and operational demands.

- **Efficient Data Storage**: Upon consumption, messages are stored in a key-value data structure, optimizing for quick access and retrieval. This approach is suitable for scenarios where speed and efficiency are paramount.

## Prerequisites
1. Java JDK 17
2. Maven
3. Git
4. Programming IDE (Intellij Recommended, but it works as well in VS Code)
5. Kafka working on default port 9092
6. Zookeeper working on default port 2181

### HOW TO:
- Have kafka and zookeeper working on the required ports ? 
1. You need to have working `Docker Desktop` installed
2. Open Command Prompt
3. Zookeeper -> `docker pull confluentinc/cp-zookeeper` -> `docker run --name my-zookeeper -p 2181:2181 -d confluentinc/cp-zookeeper`
4. Kafka -> `docker pull confluentinc/cp-kafka` -> `docker run --name my-kafka -p 9092:9092 -e KAFKA_ZOOKEEPER_CONNECT=localhost:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 -d confluentinc/cp-kafka`


## Clone the repository
If you want to clone the whole project:
`git clone https://github.com/ivan-parnarev/E-Government.git`

After you have cloned the repository -> `cd E-Government` -> `cd kafka-consumer
`
## Build the project
Once inside the project directory, you can build the project using Maven:

`mvn clean install`

If you are using VS Code you can open the folder as VS Code project and write the command directly in the VS Code terminal.

If you are using Intellij Idea you can directly open the folder as Intellij Project and this will directly install the maven dependencies. You DO NOT NEED to run the installation command !

## Starting the application
Locate the `main` method in your Spring Boot application. This is typically found in a class annotated with `@SpringBootApplication`

- Right-click on the file containing the `main` method and select `Run EGovBackendApplication`.

Once the application starts, your service is up to port `http://localhost:9393/api/v1`

## Endpoints

### Kafka Listener Management

- **Create Kafka Listener**
    - **Path**: `POST /create`
    - **DTO**: Expects JSON object with structure 
    - {
    - "topic": "topicName" 
    - }
    - **Description**: Creates and registers a Kafka listener for the specified topic.

- **Stop Kafka Listener**
    - **Path**: `POST /{listenerId}/stop`
    - **Description**: Stops the Kafka listener with the specified listener ID.

- **Start Kafka Listener**
    - **Path**: `POST /{listenerId}/start`
    - **Description**: Starts the Kafka listener with the specified listener ID.

### Kafka Message Management

- **Get Campaign Results**
    - **Path**: `GET /api/consume`
    - **Description**: Retrieves the results of the campaigns from the Kafka message catalog.

- **Delete Messages from Kafka Topic**
    - **Path**: `POST /delete/{topic}`
    - **Description**: Deletes messages from the specified Kafka topic and stops the associated listener.

## SWAGGER
SWAGGER documentation is available when the application is working on port `http://localhost:9393/swagger-ui/index.html`. You can test the endpoints and read the documentation about the logic behind each endpoint. 