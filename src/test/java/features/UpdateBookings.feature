@UpdateBooking
Feature: Update booking

  Background: Set endpoint and create new booking
    Given set endpoint as "/auth"
    And get auth token with following credentials admin & password123
    And set endpoint as "/booking"
    And the user creates a new booking with the following details:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Onur      | T.       | 100        | true        | 2018-01-01 | 2018-01-02 | komili          |
    Then the response status code should be 200


  @regression @happyPath
  Scenario: Update booking partially with basic authentication
    When user updates the following fields:
      | firstname | totalprice |
      | Spidey    | 210        |
    Then the response status code should be 200
    And the response body should match the schema UpdateBookingSchema.json
    And the response body should not be null
    And  updated fields should be updated:
      | firstname | totalprice |
      | Spidey    | 210        |


  @regression @negativeCases
  Scenario Outline: Try to send update request without token
    When user tries to update the following fields without a token:
      | firstname | Onur     |
      | <keys>    | <values> |
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
      | firstname | totalprice |
      | <keys>    | <values>   |
    Then the response status code should be <statusCode>
    And error message should be Forbidden

    Examples:
      | keys            | values     | statusCode |
      | firstname       |            |            |
      | lastname        |            |            |
      | laame           | Tarar      |            |
      | totalprice      | 100        |            |
      | totalprice      | abc        |            |
      | totalprice      |            |            |
      | totaice         |            |            |
      | depositpaid     | 231        |            |
      | depositpaid     |            |            |
      | depopaid        |            |            |
      | chein           | 2018-01-01 |            |
      | checkin         | 01-01-20   |            |
      | checkin         |            |            |
      | checkout        | 2018-01-02 |            |
      | checkout        | 01-02-2018 |            |
      | checut          | 2018-01-02 |            |
      | additionalneeds |            |            |
      | additneeds      | ad         |            |
      | additionalneeds | 123        |            |
      | additionalneeds | ***        |            |




