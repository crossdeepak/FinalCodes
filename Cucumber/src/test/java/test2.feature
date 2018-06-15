#Features
Feature: feature description
  In order to do something
  As someone
  I want something else to happen

  Scenario: testForBlankUsernameAndPassword
    Given Driver is open
    	And loginPage is there
    When Username is blank
    	And Password is blank
    Then we get error message
