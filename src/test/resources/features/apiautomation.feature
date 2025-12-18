Feature: Get API

  @api @ignore
  Scenario: Verify get details by ID
    Given I set the base URI
    When I send a GET request
    Then the response status code should be 200
    Then the response should contain "author"