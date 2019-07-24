Feature: To Demonstrate the state management and data driven testing

  Background: 
    Given url "http://localhost:8080/users/login"
  
  Scenario: Verify the login response of user user_name
    * request { userName:'#(user_name)', password: '#(pwd)' }
    * method post
    * match response.message == expectedmessage