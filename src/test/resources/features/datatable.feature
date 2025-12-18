Feature: Registration functionality


  @smoke
  Scenario Outline: Valid Registration
    Given user is on registration page "<link>"
    When validate current page "<title>"
    Then user enters firstname and lastname
    |firstname|lastname |
    | a | b |
    | c | d |
    | e | f |

    Examples:
      | link | title |
      | http://demo.automationtesting.in/Register.html | Register |