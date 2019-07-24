package com.web.utils;

import com.web.common.Constatnts;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLUtils {

  void loadDriver() {
    try {

      // LogUtil.logger.log(Level.INFO,dbProperties.toString());
      Class.forName(Constatnts.DB_DRIVER_CLASS).newInstance();
      System.out.println("*** Driver loaded");
    } catch (Exception e) {
      // LogUtil.logger.log(Level.SEVERE,"Exception Occured",e);
      e.printStackTrace();
    }
  }

  public String executeSqlUsingAnt(String sqlFilePath) {
    final class SqlExecuter extends SQLExec {
      public SqlExecuter() {
        Project project = new Project();
        project.init();
        setProject(project);
        setTaskType("sql");
        setTaskName("sql");
      }
    }
    try {
      SqlExecuter executer = new SqlExecuter();
      executer.setSrc(new File(sqlFilePath));

      executer.setDriver(Constatnts.DB_DRIVER_CLASS);
      executer.setPassword(Constatnts.DB_PASSWORD);
      executer.setUserid(Constatnts.DB_USER_NAME);
      executer.setUrl(Constatnts.DB_URL);
      executer.execute();
      return "Success";
    } catch (Exception e) {
      // LogUtil.logger.log(Level.SEVERE,"Exception Occured",e);
      e.printStackTrace();
      return e.getMessage();
    }
  }

  public  String executeScriptUsingIbatisRunner(String filePath) {

    String result = "";
    Reader reader = null;
    Connection con = null;
    try {

      ScriptRunner scriptExecutor = new ScriptRunner(getConnection());
      // scriptExecutor.setAutoCommit(dbProperties.getIbatis_autocommit());
      // scriptExecutor.setStopOnError(dbProperties.getIbatis_failon_error());
      // scriptExecutor.setDelimiter(dbProperties.getIbatis_delimitter());

      reader = new BufferedReader(new FileReader(filePath));

      scriptExecutor.runScript(reader);
      result = "success";
    } catch (Exception e) {
      result = e.getMessage();
      // LogUtil.logger.log(Level.SEVERE,"Exception Occured",e);
      e.printStackTrace();
    } finally {

      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          //  LogUtil.logger.log(Level.SEVERE,"Exception Occured",e);
          e.printStackTrace();
        }
      }

      if (con != null) {
        try {
          con.close();
        } catch (SQLException e) {
          //  LogUtil.logger.log(Level.SEVERE,"Exception Occured",e);
          e.printStackTrace();
        }
      }
    }
    return result;
  }

  public static final String URL = "jdbc:mysql://localhost/sample_schema";
  public static final String USER = "root";
  public static final String PASSWORD = "root";
  private static String INSTRUCTIONS = new String();

  public static String executeScriptUsingStatement(String filePath) {
    String result = "";
    BufferedReader reader = null;
    Connection con = null;
    Statement statement = null;
    try {

      con = getConnection();

      statement = con.createStatement();

      reader = new BufferedReader(new FileReader(filePath));
      String line = null;

      while ((line = reader.readLine()) != null) {
        // execute query
        statement.execute(line);
        result = "Success";
      }
    } catch (Exception e) {
      result = e.getMessage();
      //   LogUtil.logger.log(Level.SEVERE,"Exception Occured",e);
      e.printStackTrace();
    } finally {

      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          // LogUtil.logger.log(Level.SEVERE,"Exception Occured",e);
          e.printStackTrace();
        }
      }

      if (con != null) {
        try {
          con.close();
        } catch (SQLException e) {
          // LogUtil.logger.log(Level.SEVERE,"Exception Occured",e);
          e.printStackTrace();
        }
      }
    }

    return result;
  }

  private static Connection getConnection() {
    Connection con = null;
    try {

      con =
          DriverManager.getConnection(
              Constatnts.DB_URL, Constatnts.DB_USER_NAME, Constatnts.DB_PASSWORD);

    } catch (Exception e) {
      // LogUtil.logger.log(Level.SEVERE,"Exception Occured",e);
      e.printStackTrace();
    }
    return con;
  }

  public static void main(String[] args) {
    System.out.println(Constatnts.DB_URL);


//    try {
//
//      Connection con = getConnection();
//
//      Statement statement = con.createStatement();
//statement.execute("select * from [HMS].[dbo].Login");
//
//
//
//
//
//    } catch (Exception e) {
//
//      //   LogUtil.logger.log(Level.SEVERE,"Exception Occured",e);
//      e.printStackTrace();
//    }
  }
}
