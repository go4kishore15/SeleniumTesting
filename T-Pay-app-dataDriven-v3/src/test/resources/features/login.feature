@tag
Feature: Tpay Application testing
         Login and transfer of money 

  @sanity
  Scenario Outline: Login with valid credentials
    Given I am on Login Page
    When i fillup <username>
    And i fillingup  <password>
    And i press loginButton
    And It takes to homepage and see welcome with <username>
    And Click on pay link in homepage
    And transfer money  from acc <from> to acc <to>  for Amount <amount>
    Then check the Account Balance amount <balAmount>

    Examples: 
      | username | password  | from | to | amount | balAmount	|
      | user1    | user1pass |  1	| 2 | 100		| 900.0		|
      | user1    | user1pass |	1	| 2		| 100		|	800.0	|
      | user1    | user1pass |	1	| 2		| 100		|	700.0	|
      | user1    | user1pass |	1	| 2		| 100		|	600.0	|
      


  #@sanity
  #Scenario: Transfer money acc 1 to 2
  #@tag1
  #Scenario Outline: Login with valid credentials
    #Given I am on Login Page
    #When i fillup <username>
    #And i fillingup  <password>
    #And i press loginButton
    #Then It takes to homepage and see welcome with <username>
#
    #Examples: 
      #| username | password  |
      #| user1    | user1pass |
      #| user2    | user2pass |
