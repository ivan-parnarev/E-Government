# Kafka-Producer

## Description

Key features and functionalities include:

- **Dynamic Topic Creation**: The ability to create Kafka topics dynamically through RESTful endpoints.

- **Message Publishing**: A mechanism to send messages to Kafka topics.

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

After you have cloned the repository -> `cd E-Government` -> `cd kafka-producer
`
## Build the project
Once inside the project directory, you can build the project using Maven:

`mvn clean install`

If you are using VS Code you can open the folder as VS Code project and write the command directly in the VS Code terminal.

If you are using Intellij Idea you can directly open the folder as Intellij Project and this will directly install the maven dependencies. You DO NOT NEED to run the installation command !

## Starting the application
Locate the `main` method in your Spring Boot application. This is typically found in a class annotated with `@SpringBootApplication`

- Right-click on the file containing the `main` method and select `Run EGovBackendApplication`.

Once the application starts, your service is up to port `http://localhost:9191/api/v1`

## Endpoints

### Kafka Listener Management

- **Create Kafka Topic**
    - **Path**: `POST /topic/create`
    - **DTO**: Accepts a JSON object with a topic attribute and creates a Kafka listener for the specified topic.
    - {
    - "topic": "topicName"
    - }
    - **Description**: Creates and registers a Kafka Topic.

- **Send Message To Kafka Topic**
    - **Path**: `POST /message/send`
    - **Description**:  Sends a user voting information message to a specified Kafka topic. When topic is created the Kafka-Producer automatically connects to the Kafka-Consumer and creates a listener for the specific topic.

## SWAGGER
SWAGGER documentation is available when the application is working on port `http://localhost:9191/swagger-ui/index.html`. You can test the endpoints and read the documentation about the logic behind each endpoint. 