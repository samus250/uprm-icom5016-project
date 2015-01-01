package machete.db.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
  private static DataSource singleton = null;
  private String userName;
  private String password;
  private String dbName;

  private DataSource(String userName, String password, String dbName) throws ClassNotFoundException {
    Class.forName("org.postgresql.Driver");
    this.userName = userName;
    this.password = password;
    this.dbName = dbName;
  }

  public static DataSource getInstance() throws ClassNotFoundException {
    if (singleton == null) {
      singleton = new DataSource("machetedb", "lollipop", "machetedb");
    }
    return singleton;
  }

  public Connection getJDBCConnection() throws SQLException {

    String dbURL = "jdbc:postgresql://10.0.0.68:5432/" + this.dbName;
    Connection dbConn = null;

    try {
      dbConn = DriverManager.getConnection(dbURL, userName, password);
    } catch (SQLException e) {
      dbURL = "jdbc:postgresql://184.22.122.108:5432/" + this.dbName;
      dbConn = DriverManager.getConnection(dbURL, userName, password);
    }

    return dbConn;
  }
}
