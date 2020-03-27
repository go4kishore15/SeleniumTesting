@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag1
  Scenario Outline: Login with valid credentials
    Given I am on Login Page
    When i fillup <username>
    And i fillingup  <password>
    And i press loginButton
    Then It takes to homepage and see welcome with <username>

    Examples: 
      | username | password  |
      | user1    | user1pass |
      | user2    | user2pass |
