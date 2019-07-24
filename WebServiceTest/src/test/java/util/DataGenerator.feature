Feature: To Provide the Test Data Needed to Create a User
  Background: 
    Given url 'https://randomuser.me/api'
    * def request_Params =
  """
  {
  results:1,
  gender:male,
  nat:US,
  inc:'gender,name,location,email,login,cell'
  }
  """

    
  Scenario: Generate Random User data
    When params request_Params
    Then method get
    Then def res = response.results[0]
    Then def fullName = res.name.first + res.name.last
    Then def Street = res.location.street
    Then def city = res.location.city
    Then def state = res.location.state
    Then def postcode = res.location.postcode
    Then def latitude = res.location.coordinates.latitude
    Then def longitude = res.location.coordinates.longitude
    Then def email = res.email
    Then def username = res.login.username
    Then def password = res.login.password
    Then def cell = res.cell
      
  