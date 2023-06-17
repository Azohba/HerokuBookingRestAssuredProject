Feature: e2e Booking Feature

  Scenario: Booking e2e flow test
    Given set endpoint as "/auth"
    When get auth token with following credentials admin & password123
    Given set endpoint as "/booking"
    When the user creates a new booking with the following details:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Onur      | T.       | 100        | true        | 2018-01-01 | 2018-01-02 | komili          |
    And users get booking details with ID from created booking



