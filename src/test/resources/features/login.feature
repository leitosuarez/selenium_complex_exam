Feature: Login on saucedemo

    Background:
        Given The user enters on the login page

    #@UC1
    Scenario: Login with empty credentials
      When I enter "standard_user" in the username field
      And I enter "secret_sauce" in the password field
      And I clear the username field
      And I clear the password field
      And I click the login button
      Then I see an error message "Epic sadface: Username is required"


    Scenario: Login with empty password
      When I enter "standard_user" in the username field
      And I enter "secret_sauce" in the password field
      And I clear the password field
      And I click the login button
      Then I see an error message "Epic sadface: Password is required"


    Scenario Outline: Login with valid credentials
      When I enter "<username>" in the username field
      And I enter "<password>" in the password field
      And I click the login button
      Then I am redirected to the inventory page
      And I see the inventory page title "Swag Labs"

      Examples:
        | username | password |
        | standard_user | secret_sauce |
        | visual_user   | secret_sauce |