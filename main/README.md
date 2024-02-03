
## E-Government Backend REST API Documentation

## Introduction
To set up and run this project, you will need the following tools and environments configured on your machine:
## Prerequisites
1. Java JDK 17
2. Maven
3. Git
4. Programming IDE (Intellij Recommended, but it works as well in VS Code)

## Clone the repository
If you want to clone the whole project:
`git clone https://github.com/ivan-parnarev/E-Government.git`

If you want to clone a particular branch:
`git clone -b develop https://github.com/ivan-parnarev/E-Government.git` -> this clones the branch feature/RC-001

After you have cloned the repository -> `cd main`

## Build the project
Once inside the project directory, you can build the project using Maven:

`mvn clean install`

If you are using VS Code you can open the folder as VS Code project and write the command directly in the VS Code terminal.

If you are using Intellij Idea you can directly open the folder as Intellij Project and this will directly install the maven dependencies. You DO NOT NEED to run the installation command !

## Starting the application
Locate the `main` method in your Spring Boot application. This is typically found in a class annotated with `@SpringBootApplication`

- Right-click on the file containing the `main` method and select `Run EGovBackendApplication`.

Once the application starts, you can access it by opening your web browser and going to `http://localhost:8080`

## Endpoints Overview

### AuthenticationController
- **Authenticate user**
    - **Path**: `POST /authenticate`
    - **Description**: Authenticates a user and returns a custom authentication JWT token.

### CampaignController
- **Get voting campaign election**
    - **Path**: `GET /{electionId}/voting`
    - **Description**: Retrieves details of a specific voting campaign by ID of an election.
- **Get census campaign**
    - **Path**: `GET /{campaignId}/census`
    - **Description**: Retrieves details of a specific census campaign by ID.
- **Create voting campaign**
    - **Path**: `POST /create/vote`
    - **Description**: Creates a new voting campaign.
- **Create census campaign**
    - **Path**: `POST /create/census`
    - **Description**: Creates a new census campaign.

### CensusController
- **Census**
    - **Path**: `POST /`
    - **Description**: Saves user census data.
- **Census questions**
    - **Path**: `GET /questions`
    - **Description**: Retrieves all census questions along with their answers.

### VoteController
- **Voting**
    - **Path**: `POST /`
    - **Description**: Saves user voting data.
    -
### RegionController
- **Regions**
    - **Path**: `GET /`
    - **Description**: Lists all regions.

## SWAGGER
SWAGGER documentation is available when the application is working on port `localhost:8080/swagger-ui/index.html`. You can test the endpoints and read the documentation about the logic behind each endpoint. 