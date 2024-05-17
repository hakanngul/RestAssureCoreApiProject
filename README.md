# Rest Assured Core API Project

This project is a comprehensive test framework built using Java, Rest Assured, and Cucumber. The framework is designed to facilitate API testing with structured configuration, logging, reporting, and robust assertions.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Introduction

The Rest Assured Core API Project provides a robust structure for API testing using Rest Assured and Cucumber. It includes various utility classes and handlers to simplify test setup, execution, and reporting.

## Features

- **Configurable Environment:** Easily switch between different environments using the configuration properties.
- **Authentication Management:** Handles authentication for API requests.
- **Retry Mechanism:** Implements retry logic for handling intermittent failures.
- **Extensive Logging:** Uses Log4j2 for detailed logging.
- **Comprehensive Reporting:** Generates detailed HTML reports using ExtentReports.
- **Flexible Assertions:** Provides a variety of assertion methods to validate API responses.

## Installation

**Clone the Repository:**
```sh
git clone https://github.com/hakanngul/RestAssureCoreApiProject.git
```


## Navigate to the Project Directory

```sh
cd RestAssureCoreApiProject
```

## Install Dependencies

Ensure you have Maven installed, then run:

```sh
mvn clean install
```

## Usage

### Running Tests

To run the tests, use the following command:

```sh
mvn test
```

## Project Structure

```lua
core/
|-- src/
|   |-- main/
|       |-- java/
|           |-- core/
|               |-- authentication/
|               |-- base/
|               |-- config/
|               |-- handlers/
|               |-- logging/
|               |-- request/
|               |-- utils/
|       |-- resources/
|           |-- config.properties
|   |-- test/
|       |-- java/
|           |-- core/
|               |-- AppTest.java
|-- pom.xml
```

## Configuration

The `config.properties` file located in `src/main/resources` contains all the necessary configurations for the framework. Update this file to match your environment settings.

### Example config.properties:

```properties
# API base URL
base.url=https://jsonplaceholder.typicode.com

# Authentication credentials
username=testuser
password=testpassword

# Environment settings
environment=test

# Timeout settings
request.timeout=5000

# Retry policy settings
retry.maxAttempts=3
retry.delay=1000

# Log settings
log.level=DEBUG
log.filepath=logs/app.log
```

## Running Tests

Run all tests:

```sh
mvn test
```

Run specific tests:
Use the `-Dtest` option to specify test classes or methods.

```sh
mvn -Dtest=AppTest test
```

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch.
3. Make your changes.
4. Submit a pull request.

Please ensure your code follows the project's coding standards and includes appropriate tests.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

Hakan GÜL - [GitHub](https://github.com/hakangul) - [LinkedIn](https://www.linkedin.com/in/hakangul)
