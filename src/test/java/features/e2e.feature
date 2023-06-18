@e2e
Feature: e2e Booking Feature


  Scenario: Booking e2e flow test
    When get auth token with following credentials admin & password123
    When the user creates a new booking with the following details:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Onur      | T.       | 100        | true        | 2018-01-01 | 2018-01-02 | komili          |
    And users get booking details with ID from created booking
    And user updates the following fields:
      | firstname | totalprice |
      | Spidey    | 210        |
    And user deletes booking with ID
    And users get booking details with ID from created booking
    Then error message should be Not Found



