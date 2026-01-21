Feature: Registration functionality

  Background:
    #Given user is on registration page "<link>"

  @smoke @ignore
  Scenario Outline: Valid Registration
    Given user is on registration page "<link>"
    When validate current page "<title>"
    Then user enters "<firstname>" and "<lastname>"
    Then read value from properties file
    Then read cell value from excel
    Then write cell value in excel
    Then create .xlsx file and sheet

    Examples:
      | firstname | lastname | link | title |
      |  aa    |  bb   | http://demo.automationtesting.in/Register.html | Register |
   #   |  a        |  b       | https://www.google.com/ | Googl                        |