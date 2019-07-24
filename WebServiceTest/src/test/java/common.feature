Feature: An interface to give all the re usable methods to the feature files.

  Background: 
#    Given url ''

  Scenario: Data Read and Write Functionality
    * def PropertyReader =
    """
    function (key)
    {

    var DataStorage = Java.type('com.mh.utils.DataStorage');
    var ds = new DataStorage();
    return ds.read(key);
    }
    """

    * def WritePropertyFile =
   """
   function(key,value) {
   var DataStorage = Java.type('com.mh.utils.DataStorage');
   var dS = new DataStorage();
   return dS.write(key,value);
   }
   """




    * def executeSQLScript =

    """
    function()
    {
    var SQLExecutor = Java.type('com.mh.utils.SQLUtils')
    var sql = new SQLExecutor()
    return sql.executeSqlUsingAnt(sqlScript)
    }
    """



#    * def basicToken = BasicToken('dev' , 'mhrulez')
#    * def newUserToken = BasicToken ('binu1' , 'ad')


    * def currentTimeInMilliSeconds =
    """
    function()
    {
    var basic = Java.type('com.mh.api.rest.token.BasicTokenProvider');
    var getT = new basic();

    return getT.getCurrentTime();
    }
    """

    * def basicToken = BasicToken('dev' , 'mhrulez')
    * def newUserToken = BasicToken ('joemon' , 'ad')
    * def currentTime = currentTimeInMilliSeconds()








