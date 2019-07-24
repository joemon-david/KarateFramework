Feature: To execute the login call with different data and validate the response

  @Login
  Scenario Outline: Login with user multi user check - <uName>
    * def SQLExecutor = Java.type('com.mh.utils.SQLUtils')
    * def sql = new SQLExecutor()
    * def dataSetUp = sql.executeScriptUsingIbatisRunner(<create_script>)
    * match dataSetUp == 'success'
    * def resp = call read('classpath:demo/Login.feature') { user_name : <uName> , pwd: <password> ,expectedmessage:<expected_message> }
#    * match resp.message == <expected_message>
    * def clear = sql.executeScriptUsingIbatisRunner(<clear_script>)
    * match clear == 'success'

    Examples:
    |create_script | uName | password | expected_message | clear_script |
    |createActiveUserScript |'active' |'mhauto'|'Login success'| deleteActiveUserScript|
    |createExpiredUserScript |'expired' |'mhauto'|'Password Expired'| deleteExpiredUserScript|
    |createNoAccessUserScript |'noaccess' |'mhauto'|'User does not have sufficient access'| deleteNoAccessUserScript|

