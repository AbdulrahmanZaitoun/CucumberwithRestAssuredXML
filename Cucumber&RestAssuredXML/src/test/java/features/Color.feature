Feature: 
  Verify color lovers API

  Scenario: Verify Rest service Get Method
    Given I want to execute get color lovers patterns
    When I submit the Get request
    Then I should get all the patterns numViews greater than 4000