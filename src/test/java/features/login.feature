Feature: Login into Application

Scenario Outline: Positive test validating login
    Given Initialize the browser with chrome
    And Navigate to "https://rahulshettyacademy.com/" site
    And Click on login link in home page to land on sign in Page
    When User enters <username> and <password> and logs in
    Then Verify that user is succesfully logged in
		And close browser
Examples:
|username						|password		|
|thu.exp@gmail.com	|Minhthu@89	|
|test@gmail.com			|123456			|
	