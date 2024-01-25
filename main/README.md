
## Backend REST Api Documentation

## Introduction
This project is a Spring Boot application built with Maven.The following guide will help you clone the project, build it, and run it on your local machine.
E-Government enables voters to cast their votes for candidates or policies from any location, using a computer or a mobile device.

## Prerequisites
1. Java JDK 17
2. Maven
3. Git
4. Programming IDE (Intellij Recommended, but it works as well in VS Code)
5. Redis that is working at port 6379


## Clone the repository 
If you want to clone the whole project:
`git clone https://github.com/ivan-parnarev/E-Government.git`

If you want to clone a particular branch: 
`git clone -b feature/RC-001 https://github.com/ivan-parnarev/E-Government.git` -> this clones the branch feature/RC-001

After you have cloned the repository -> `cd e-gov-backend`
   
## Build the project 
Once inside the project directory, you can build the project using Maven:

`mvn clean install`

If you are using VS Code you can open the folder as VS Code project and write the command directly in the VS Code terminal.

If you are using Intellij Idea you can directly open the folder as Intellij Project and this will directly install the maven dependencies. You DO NOT NEED to run the installation command !

## Starting the application
Locate the `main` method in your Spring Boot application. This is typically found in a class annotated with `@SpringBootApplication`

- Right-click on the file containing the `main` method and select `Run EGovBackendApplication`.

Once the application starts, you can access it by opening your web browser and going to `http://localhost:8080` 

## Endpoints

 UserVoteController
- GET /api/votes - retrieve information about all the users in the database. Returns `Response.ok`
- POST /api/votes - accepts `@RequestBody` and creates an object of `User.class` that is saved in the database. Returns `Response.ok`

## SWAGGER 
SWAGGER documentation is available when the application is working on port `localhost:8080/swagger-ui/index.html`. You can test the endpoints and read the documentation about the logic behind each endpoint. 