Feature: Get Bookings

 # Background:
 #   Given get auth token with following credentials:
 #     | username | admin       |
#| password | password123 |
 #   And users create a new booking with the following details:
 #     | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
 #     | Onur      | T.       | 100        | true        | 2018-01-01 | 2018-01-02 | komili          |

  @regression @happyPath
  Scenario: Get all bookings without filters
    When users get all bookings
    Then the response status code should be 200
    And the response body should not be null
    And the response body should match the schema "GetBookingsIDSchema.json"
    And the response body should contain ID field values

  @regression @happyPath
  Scenario: Get booking details by ID
    Given users get all bookings
    When users get booking details with ID
    Then the response status code should be 200
    And the response body should not be null
    And the response body should match the schema "GetBookingsSchema.json"


  @regression @happyPath
  Scenario Outline: Get booking with parameters
    Given users create a new booking with the following details:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Onur      | T.       | 100        | true        | 2018-01-01 | 2018-01-02 | komili          |
    When users try to get booking with "<keys>" and "<values>" parameters
    Then the response status code should be 200
    And the response body should not be null
    And the response body should match the schema
    And the response body should contain ID that belongs to <queryParam>
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
    Then the response status code should be a non-200 status code
    And the response body should not be null
    And the response body should contain ID that belongs to <queryParam>
    Examples:
      | keys            | values     |
      | firstname       |            |
      | lastname        |            |
      | lastname        | Tarar      |
      | totalprice      | 100        |
      | depositpaid     | true       |
      | checkin         | 2018-01-01 |
      | checkout        | 2018-01-02 |
      | additionalneeds | komili     |