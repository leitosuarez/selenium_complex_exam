Feature: Login functionality in SauceDemo

  Scenario Outline: Login validation scenarios
    Given the user selects a random valid username
    And the user opens the browser "<browser>"
    And the user is on the login page
    When the user logs in with username "<username>" and password "<password>"
    Then the expected result should be "<expected>"

    Examples:
      | browser  | username | password       | expected                |
      | edge     |          |                | Username is required    |
      | firefox  |          |                | Username is required    |
      | edge     | valid    |                | Password is required    |
      | firefox  | valid    |                | Password is required    |
      | edge     | valid    | secret_sauce   | Swag Labs               |
      | firefox  | valid    | secret_sauce   | Swag Labs               |