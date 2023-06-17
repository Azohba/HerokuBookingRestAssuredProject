@getbookings
Feature: Get Bookings

  Background: Set endpoint
    Given set endpoint as "/booking"

  @regression @happyPath
  Scenario: Get all bookings without filters
    When users get all bookings
    Then the response status code should be 200
    And the response body should not be null
    And the response body should match the schema GetBookingsIDSchema.json
    And the response body should contain ID field values

  @regression @happyPath
  Scenario: Get booking details by ID
    Given users get all bookings
    When users get booking details with ID
    Then the response status code should be 200
    And the response body should not be null
    And the response body should match the schema GetBookingsSchema.json


  @regression @happyPath
  Scenario Outline: Get booking with query parameters
    Given users create a new booking with the following details:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Onur      | T.       | 100        | true        | 2018-01-01 | 2018-01-02 | komili          |
    When users try to get booking with "<keys>" and "<values>" parameters
    Then the response status code should be 200
    And the response body should not be null
    And the response body should match the schema GetBookingsIDSchema.json
    And the response body should contain ID that belongs to created booking
    Examples:
      | keys            | values     |
      | firstname       | Onur       |
      | lastname        | T.         |
      | totalprice      | 100        |
      | depositpaid     | true       |
      | checkin         | 2018-01-01 |
      | checkout        | 2018-01-02 |
      | additionalneeds | komili     |

  @regression @negativeCases
  Scenario Outline: Get booking with parameters negative test cases
    When users try to get booking with "<keys>" and "<values>" parameters
    Then the response status code should be 200
    And the response body should not be null

    Examples:
      | keys            | values     |
      | firstname       |            |
      | ftname          | Onur       |
      | lastname        |            |
      | laame           | Tarar      |
      | totalprice      | 100        |
      | totalprice      | abc        |
      | totalprice      |            |
      | totaice         |            |
      | depositpaid     | 231        |
      | depositpaid     |            |
      | depopaid        |            |
      | chein           | 2018-01-01 |
      | checkin         | 01-01-20   |
      | checkin         |            |
      | checkout        | 2018-01-02 |
      | checkout        | 01-02-2018 |
      | checut          | 2018-01-02 |
      | additionalneeds |            |
      | additneeds      | ad         |
      | additionalneeds | 123        |
      | additionalneeds | ***        |