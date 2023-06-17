@deletebooking
Feature: Delete booking feature

  Background: Create booking before delete booking
    Given set endpoint as "/booking"
    When users create a new booking with the following details:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Onur      | T.       | 100        | true        | 2018-01-01 | 2018-01-02 | komili          |
    Then  the response status code should be 200


  Scenario: Delete booking
    Given set endpoint as "/auth"
    And get auth token with following credentials admin & password123
    And set endpoint as "/booking"
    When delete booking with ID
    Then users get booking details with ID



