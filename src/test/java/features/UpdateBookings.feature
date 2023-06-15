@booking

Feature: Update booking

  @regression @happyPath
  Scenario : Update booking partially with basic authentication
    When user update following fields:
      | firstname  | Onur |
      | totalprice | 210  |
    Then check status code is "200"
    And check reponse schema is true
    And check reponse body is not null
    And check firstname is updated
    And check totalprice is updated


  @regression @happyPath
  Scenario : Try update without token
    When user update following fields:
      | firstname  | Onur |
      | totalprice | 210  |
    Then check status code is 200
    And check reponse schema is true
    And check reponse body is not null
    And check response message is forbidden







