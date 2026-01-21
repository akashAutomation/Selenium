Feature: Get API

  @api @ignore
  Scenario: GET details
    Given I set the base URI
     |https://library-api.postmanlabs.com|
    When I send a GET request with endpoint "/books"
    Then the response status code should be 200
    Then the response should contain "author"

  @api @ignore
  Scenario: POST, PATCH and DELETE details
    Given I set the base URI
      |https://library-api.postmanlabs.com|
    When I send a POST request
    Then the response status code should be 201
    When I send a PATCH request
    Then the response status code should be 200
    When I send a DELETE request
    Then the response status code should be 204

  @api @ignore
  Scenario: path and query parameter
    Given I set the base URI
      |https://library-api.postmanlabs.com|
    When I send a GET request using path and query parameter
    Then the response status code should be 200

  @api
  Scenario: get cookie and headers
    Given I set the base URI
      |https://www.google.com/|
    When I send a GET request with endpoint ""
    Then the response status code should be 200
    Then get cookie
    Then get headers