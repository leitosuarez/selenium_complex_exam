Feature: Login functionality in SauceDemo

  Scenario Outline: Login validation scenarios
    Given the user is on the login page
    When the user logs in with username "<username>" and password "<password>"
    Then the expected result should be "<expected>"

    Examples:
      | username        | password       | expected                |
      |                 |                | Username is required    |
      | valid_user      |                | Password is required    |
      | standard_user   | secret_sauce   | Swag Labs               |