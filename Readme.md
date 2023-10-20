# Management API
Management API for performing CRUD operation on users and roles.

## Table of Contents
+ [About](#about)
+ [Getting Started](#getting_started)
+ [Usage](#usage)

## About <a name = "about"></a>
The purpose of this project is to perform CRUD operation of user and roles. In addition, 
it can be used as starter app for building an application.

## Getting Started <a name = "getting_started"></a>
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them.

```
Java 8
Gradle 7.6.1
Spring 2.7.12
```

### Installing

A step by step series of examples that tell you how to get a development env running.

In order to run in development mode
```
1) ./gradlew clean build
2) java -jar .\build\libs\calculator-0.0.1-SNAPSHOT.jar com.globalsavings.calculator.CalculatorApplication
```
## Execution
### Running in development mode
```
1) ./gradlew clean build
2) java -jar .\build\libs\management-0.0.1-SNAPSHOT.jar com.invent.management.ManagementApplication
```

### Testing
```
./gradlew test
```

### Runing Application through Docker
Running through Docker
```
1) docker build -t management/management .
2) docker run -p 8080:8080 management/management
``````

## Usage <a name = "usage"></a>

There are APIs which split into two groups. These groups are <b>User</b> and <b>Role</b>.