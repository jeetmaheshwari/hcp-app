package com.hcp.datasource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DatabaseManager
{
  private static DataSource ds;
  private static Statement stmt;
  private static String className = DatabaseManager.class.getName();
  
  private DatabaseManager()
  {
    try
    {
      InitialContext ctx = new InitialContext();
      ds = (DataSource)ctx.lookup("java:comp/env/jdbc/DefaultDB");
      
      stmt = setConnection();
    }
    catch (Exception e)
    {
      Logger.getLogger(className).log(Level.SEVERE, null, e);
    }
  }
  
  private static Statement setConnection()
  {
    try
    {
      Connection connection = ds.getConnection();
      return connection.createStatement();
    }
    catch (SQLException ex)
    {
      Logger.getLogger(className).log(Level.SEVERE, null, ex);
    }
    return null;
  }
  
  private static class DbSingletonManagerHolder
  {
    private static final DatabaseManager instance = new DatabaseManager();
  }
  
  public static DatabaseManager getInstance()
  {
    try
    {
      return DbSingletonManagerHolder.instance;
    }
    catch (ExceptionInInitializerError localExceptionInInitializerError) {}
    return null;
  }
  
  public static Statement getStatement()
  {
    return stmt;
  }
}
