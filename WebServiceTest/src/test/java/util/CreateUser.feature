Feature: To Call Re usable Data Generator
  Background:
    * def data = callonce read('DataGenerator.feature')

    Scenario: Print Values
      * print data.fullName
      * print data.email
      * print data.username
      * print data.password
      * print data.cell