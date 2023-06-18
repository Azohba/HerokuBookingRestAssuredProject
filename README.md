# Booking API Rest Assured Project

## Table of Contents
- Rest Assured - v5.3.0
- Junit 4.13.2
- Fasterxml 2.94
- Lombok 1.18.20
- Slf4j 1.7.30
- Rest assured schema validator 5.3.0
- Cucumber picocontainer 7.12.1
- Utilities 1.4.5
- Cucumber java 7.12.1
- Cucumber core 7.11.0
- Cucumber junit 7.11.0
- Maven surefire 2.22.1

## Installation 
* Java should be installed and configured
* Maven should be installed 
* You can install "mvn clean install" command

## Usage 
* You can run all test cases with "mvn test" command
* All test cases are stored in the "test/features" package. You can also run specific features from those files
* If you want to run your test cases with Cucumber tags => "mvn test -Dcucumber.options="--tags @regression""

## Configuration 
* You need to set "test/resources" file as a Test Root file on "file/project structure/Modules" (I couldn't understand why it came standard package when pulling the code from GitHub)

## Features 
1. It generates Cucumber report with all the step details. Reports will be generated in both HTML & JSON file format in "target/reports"
2. This also has an example to validate response body using JSON schema and Java POJO classes.
3. Test execution can be triggered from the command line.
4. Easy integration into CI/CD pipeline.

## Documentation
* [API Documentation](https://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-GetBooking)

## Notes
I've written all test assertions by accepting the current behavior of the Heroku application as acceptance criteria. However, below scenarios should have failed in accordance with real backend behaviors:

1. Status codes are not returned correctly when you send POST, PUT, and DELETE requests.
2. If you send negative query params, it returns a fulfilled response body.
3. There is no control to check-in and check-out dates. You can use any dates you want.
