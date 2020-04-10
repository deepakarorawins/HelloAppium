Feature: Hello ios World

  Scenario: Computing sum of two numbers
    As a user when I add two numbers 22 and 33 I should see the sum 55

    When I launch ios app
    And I choose to enter "22" and "33"
   # And I choose to enter "33"
    And I tap on Compute Sum
    Then I should see the result "55"