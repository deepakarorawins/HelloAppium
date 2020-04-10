# new feature
# Tags: optional

Feature: As a user I want to convert units

  Scenario: Registration flow validation via app
    As a user I should be able to see my google account when I try to register myself to Quikr

    When I launch the Onekey app
    Then I see Home page screen with "Sign In" button

  Scenario: Registration flow validation via web
  As a user I should be able to see Sign Up button when I click Sign Up tab

    When I launch the Onekey mobile web app
    And I choose to register
    Then I should see "Sign Up" button

  Scenario: Registration Flow Validaton via Quikr App
  As a user I shold be able to see my google account when I try to register myself in quikr

    When I launch the Quikr app
    And I choose to log in using Google
    Then I see account picker screen with my email address "testmail@gmail.com"

  @search
  Scenario: Search for used Honda City car in Banglore city

    When I launch the Quikr app
    And I choose "Bangalore" as my city
    And I search for "Honda City" under Used Cars
    Then I should see the first car search result with "Honda"