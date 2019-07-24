package com.web.common;

import com.web.utils.DataStorage;

public interface Constatnts {

  String USER_DIR = System.getProperty("user.dir");
  String DATA_STORE_PROPERTY_FILE = USER_DIR + "/src/test/java/data.properties";
  DataStorage dataStorage = new DataStorage();
  /*SQL Util Constants*/
  final String DB_DRIVER_CLASS = dataStorage.read("driver_class");
  final String DB_URL = dataStorage.read("db_url");;
  final String DB_USER_NAME = dataStorage.read("db_username");;
  final String DB_PASSWORD = dataStorage.read("db_password");
  final String NEW_LINE_CHAR = "\n";

}
