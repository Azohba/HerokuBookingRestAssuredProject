# Booking API Rest Assured Project

## Overview
Api Test cases were implemented by using Java language, Maven Framework and Rest Assured with BDD structure.

## Table of Contents
- **Rest Assured** - v5.3.0
- **Java** 17
- **Junit** 4.13.2
- Fasterxml 2.94
- Lombok 1.18.20
- Slf4j 1.7.30
- Rest assured schema validator 5.3.0
- Cucumber picocontainer 7.12.1
- Utilities 1.4.5
- **Cucumber java** 7.12.1
- Cucumber core 7.11.0
- Cucumber junit 7.11.0
- Maven surefire 2.22.1

## Installation 
* Java should be installed (If Windows it should be configured)
* Maven should be installed (If Windows it should be configured)

## Structure
Automated tests are placed into the `test/features`. Here is the detailed structure of my project.

    .src
    ├── main
    │   ├── models          
    │   │   ├── request      # stores payloads for requests
    │   │   └── response     # store json object models for responses
    ├── test                 
    │   ├── client           # Rest Assured api call helpers
    │   ├── config           # stores BaseUrl and endpoint constants
    │   ├── features         # stores all automated tests' feature files
    │   │   ├── steps        # stores all feature files' step definition classes
    │   ├── helpers          # stores all main request calls for the main api
    │   ├── resources        # stores all response schemas
    │   └── TestRunner       # class to execute tests by JUnit
    └── ...

## Usage/Execution
* You can run all test cases with the following command on your terminal.
```
mvn test
```
* All test cases are stored in the "test/features" package. You can also run specific features from those files by using Cucumber Java running configuration.

## Features 
1. Generates a Cucumber report with all the step details. Reports will be generated in both HTML & JSON file format in "target/reports"
   <img src="https://github.com/Azohba/HerokuBookingRestAssuredProject/assets/75690034/2ac3ccc7-220c-4711-9319-dbd60e25281d" width="650" height="450">
2. Added specific validations on the response body by using JSON schema and Java POJO classes.
3. Easy integrable with CI/CD pipeline in regards to parallel execution.
   

## Documentation
Automated tests application's document.
* [API Documentation](https://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-GetBooking)
  

## Notes
I've written all test assertions by accepting the current behavior of the Heroku application as acceptance criteria. However, the below scenarios should have failed in accordance with real backend behaviors:
1. Status codes are not returned correctly when I send POST, PUT, and DELETE requests. The response codes should be tailored according to the HTTP request method. For instance, it should return 201 when I call a POST request for creating a booking.
2. If you send negative query parameters, it returns a fulfilled response body. However, I expected to see some specific errors. For instance when I send a PATCH call within null firstname in update booking payload
3. There is no control over check-in and check-out dates. You can use any dates you want.
4. Herokuapp is unstable by considering expected behaviors. As a result of this situation test results could differ.
