Feature: Authentication
  #DONE
  @regression @happyPath
  Scenario: Get Auth token
    When get auth token with following credentials:
      | username | admin       |
      | password | password123 |
    Then the response status code should be 200
    And the token value should not be null

  @regression @negativeCases
  Scenario Outline: Auth negative test cases
    When send <username> and <password>
    Then the response status code should be 200
    And error message should be <message> for authentication
    Examples:
      | username | password      | message         |
      | admin    | null          | Bad credentials |
      | admin    | 123           | Bad credentials |
      | username | password123   | Bad credentials |
      | null     | password123   | Bad credentials |
      |          | password123!! | Bad credentials |