Feature: Create Booking

  @regression @happyPath @createBooking
  Scenario: Create new booking
    When the user creates a new booking with the following details:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Onur      | T.       | 100        | true        | 2018-01-01 | 2018-01-02 | komili          |
    Then the response status code should be 200
    And the response body should not be null
    And the response body should match the schema CreateBookingSchema.json
    And the response body should contain created booking details

  @regression @negativeCases @createBooking
  Scenario Outline: Create new booking negative scenarios
    When a user tries to create a booking with invalid data
      | firstname   | lastname   | totalprice   | depositpaid   | checkin   | checkout   | additionalneeds   |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    Then the response status code should be <statusCode>
    And  error message should be <errorMessage>

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds | statusCode | errorMessage          |
      |           | Lastname | 100        | true        | 2023-01-01 | 2023-01-02 | None            | 500        | Internal Server Error |
      | John      |          | -100       | true        | 2023-01-01 | 2023-01-02 | None            | 500        | Internal Server Error |
      | John      | Doe      |            | true        | 2023-01-02 | 2023-01-01 | None            | 500        | Internal Server Error |
      | John      | Doe      | 100        |             | 2023-01-02 | 2023-01-02 | None            | 500        | Internal Server Error |
      | John      | Doe      | 100        | true        |            | 2023-01-02 | None            | 500        | Internal Server Error |
      | John      | Doe      | 100        | true        | 2023-01-01 |            | None            | 500        | Internal Server Error |