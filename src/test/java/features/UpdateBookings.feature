@UpdateBooking
Feature: Update booking

  Background: Get Token and create new booking
    Given get auth token with following credentials admin & password123
    And the user creates a new booking with the following details:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Onur      | T.       | 100        | true        | 2018-01-01 | 2018-01-02 | komili          |
    And the response status code should be 200


  @regression @happyPath
  Scenario: Update booking partially with basic authentication
    When user updates the following fields:
      | firstname  | Onn |
      | totalprice | 22  |
    And users get booking details with ID from created booking
    Then the response status code should be 200
    And the response body should match the schema UpdateBookingSchema.json
    And the response body should not be null
    And updated fields should be changed
      | firstname  | Onn |
      | totalprice | 22  |


  @regression @negativeCases
  Scenario Outline: Try to send update request without token
    When user tries to update the following fields without a token:
      | <keys> | <values> |
    Then the response status code should be 403
    And error message should be Forbidden

    Examples:
      | keys            | values     |
      | firstname       | Onur       |
      | lastname        | TT         |
      | totalprice      | 100        |
      | depositpaid     | 231        |
      | checkin         | 01-01-20   |
      | checkout        | 2018-01-02 |
      | additionalneeds | 123        |

  @regression @negativeCases
  Scenario Outline: Partially update negative scenarios
    When user updates the following fields:
      | <keys> | <values> |
    Then the response status code should be <statusCode>

    Examples:
      | keys            | values     | statusCode |
      | firstname       |            | 200        |
      | lastname        |            | 200        |
      | laame           | Tarar      | 200        |
      | totalprice      | 100        | 200        |
      | totalprice      | abc        | 200        |
      | totalprice      |            | 200        |
      | totaice         |            | 200        |
      | depositpaid     | 231        | 200        |
      | depositpaid     |            | 200        |
      | depopaid        |            | 200        |
      | chein           | 2018-01-01 | 200        |
      | checkin         | 01-01-20   | 200        |
      | checkin         |            | 200        |
      | checkout        | 2018-01-02 | 200        |
      | checkout        | 01-02-2018 | 200        |
      | checut          | 2018-01-02 | 200        |
      | additionalneeds |            | 200        |
      | additneeds      | ad         | 200        |
      | additionalneeds | 123        | 200        |
      | additionalneeds | ***        | 200        |




