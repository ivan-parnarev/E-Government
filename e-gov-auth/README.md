# Java Spring Authentication Service
## Introduction

This Java Spring Authentication Service (REST API) is part of the E-Government Project.
It is designed to handle user authentication using JSON Web Tokens (JWT) and verify the existence of users in the database,
during Voting and Census Campaigns.

## Table of Contents

1. [Features](#features)
1. [Technologies Used](#technologies used)
1. [Setup](#setup)
1. [Usage](#usage)
1. [License](#license)

## Features

* **User Authentication**: Verifies user existence in the database.
* **JWT Token Generation**: Generates JWT tokens upon successful user verification.
* **Private Key Encryption**: Uses a private key for token encryption.
* **Public Key**: Provides Public Key for token decryption upon successful verification.

## Technologies Used

* **Java:** Programming language used for backend development.
* **Spring Boot:**  Framework for building Java-based applications.
* **Spring Security:**  Provides security configurations for authentication and authorization.
* **JSON Web Token(JWT):** Token-based authentication mechanism.
* **Database PostgreSQL:** Stores user information.

## Setup
### Prerequisites

* Java Development Kit (JDK) installed, with Java v.17 (or higher)
* PostgreSQL Database installed
* Public/Private Key pair generated for JWT encryption/decryption with openSSL (Already generated and located in resources/keys directory),
you can generate your own key pair.

### Configuration

1. Clone the repository:
   * `git clone https://github.com/ivan-parnarev/E-Government.git`
   * After you have cloned the repository -> cd e-gov-auth
   * Update application properties (application.yml) with database connection details

1. Build the project:
   * Once inside the project directory, you can build the project using Maven: `mvn clean install`

1. Starting the Application:
   * Locate the main method in your Spring Boot application. This is typically found in a class annotated with @SpringBootApplication
   * Right-click on the file containing the main method and select Run EGovAuthApplication.
   * Backend Application will start on port 8081, you can access it by making requests to http://localhost:8081


## Usage

* When you start the application there will be three users already created in the database, each user has:
    - First Name, middle name, last name and hashed userPin
* Send a POST request to http://localhost:8081/api/v1/authenticate with user credentials(hashed userPin).
  The service will verify the user existence in the database and return a JWT token upon successful authentication, as well as Public Key to decrypt the encrypted  token

## License

This project is licensed under the MIT License.