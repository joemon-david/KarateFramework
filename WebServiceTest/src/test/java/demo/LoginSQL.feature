Feature: To set up the test data for Login check

  Scenario: Call the SQL Utility method
    * def SQLExecutor = Java.type('com.web.utils.SQLUtils')
    * def sql = new SQLExecutor()
    * def exectionResult = sql.executeSqlUsingAnt(sqlScriptName)