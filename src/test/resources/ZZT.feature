Feature: ZZT services scenarios


  Scenario: Auth request
    Given  I set the url "https://reqres.in/api/"
    Given  I set the header property "content-type" value "application/json; charset=utf-8"
    Given I call the METHOD "post" RESPONSE BODY "login" PATH "login" PATH PARAMETER "" BODY PARAMETER ""
    Then The status code is equals to "200"
    Then The response time should be less than "2L" seconds

  Scenario: Post register
    Given  I set the url "https://reqres.in/api/"
    Given  I set the header property "content-type" value "application/json; charset=utf-8"
    Given I call the METHOD "post" RESPONSE BODY "AddnewUser" PATH "register" PATH PARAMETER "" BODY PARAMETER ""
    Then The status code is equals to "200"
    Then The response time should be less than "3L" seconds


  Scenario: Get list of users
    Given  I set the url "https://reqres.in/api/"
    Given  I set the header property "content-type" value "application/json; charset=utf-8"
    Given I call the METHOD "get" RESPONSE BODY "" PATH "users?page=2" PATH PARAMETER "" BODY PARAMETER ""
    Then The status code is equals to "200"
    Then The property from response body  "data.first_name[1]" is equals to "Charles"
    Then The response time should be less than "3L" seconds


  Scenario: Update exist user
    Given  I set the url "https://reqres.in/api/"
    Given  I set the header property "content-type" value "application/json; charset=utf-8"
    Given I call the METHOD "put" RESPONSE BODY "newUser" PATH "/users/2" PATH PARAMETER "" BODY PARAMETER ""
    Then The status code is equals to "200"
    Then The response time should be less than "6L" seconds