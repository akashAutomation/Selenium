Feature: Validate gillete login page

Scenario: validate login page scenario with invalid username and password

Given user open the url
When user click on profile icon
Then Login page should open
Then user enters invalid username and password
Then user clicks on login button
Then validate user should not able to login and validate the error msg
