@deletebooking
Feature: Delete booking feature

  Background: Create booking before delete booking
    Given get auth token with following credentials admin & password123
    And the user creates a new booking with the following details:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Onur      | T.       | 100        | true        | 2018-01-01 | 2018-01-02 | komili          |
    And  the response status code should be 200

  @regression @happyPath
  Scenario: Delete booking
    When user deletes booking with ID
    And the response status code should be 201
    And users get booking details with ID from created booking
    Then error message should be Not Found

  Scenario Outline: User tries to delete booking invalid token
    When user tries to delete booking with invalid <token>
    Then the response status code should be 403
    And error message should be Forbidden
    Examples:
      | token |
      | " "   |
      | "123" |
      |       |

  Scenario Outline:  User tries to delete booking invalid ID
    When user send invalid <ID> path parameter
    Then the response status code should be 403
    And error message should be Forbidden
    Examples:
      | ID    |
      | " "   |
      | "abc" |
      |       |





