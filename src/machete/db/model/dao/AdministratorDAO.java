package machete.db.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import machete.db.model.dto.Administrator;

public class AdministratorDAO {
  private static AdministratorDAO singleton;

  private static final String ADMINISTRATOR_BY_ID = "select user_id, user_name, user_password, user_email, "
      + "administrator_level " + "from administrator where user_id = ?";

  private static final String ADMINISTRATOR_BY_USERNAME = "select user_id, user_name, user_password, user_email, "
      + "administrator_level " + "from administrator where user_name = ?";

  private static final String INSERT_ADMINISTRATOR = "insert into administrator (user_name, user_password, user_email) "
      + "values (?, ?, ?);";

  private static final String UPDATE_ADMINISTRATOR_BY_ID = "update administrator set user_password=? where user_id=?";

  private static final String DELETE_ADMINISTRATOR = "delete from administrator where user_id = ?";

  private AdministratorDAO() {

  }

  public static AdministratorDAO getInstance() {
    if (singleton == null) {
      singleton = new AdministratorDAO();
    }
    return singleton;
  }

  public Administrator getAdministrator(long userId) throws DataSourceException {
    Connection conn = null;
    Administrator result = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(ADMINISTRATOR_BY_ID);
      stmt.setLong(1, userId);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        result = new Administrator();
        result.setUserId(rs.getLong(1));
        result.setUserName(rs.getString(2));
        result.setUserPassword(rs.getString(3));
        result.setUserEmail(rs.getString(4));
        result.setAdministratorLevel(rs.getString(5));
      }
      return result;
    } catch (Exception e) {
      throw new DataSourceException("Unable to read data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }

  public Administrator getAdministratorByUsername(String userName) throws DataSourceException {
    Connection conn = null;
    Administrator result = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(ADMINISTRATOR_BY_USERNAME);
      stmt.setString(1, userName);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        result = new Administrator();
        result.setUserId(rs.getLong(1));
        result.setUserName(rs.getString(2));
        result.setUserPassword(rs.getString(3));
        result.setUserEmail(rs.getString(4));
        result.setAdministratorLevel(rs.getString(5));
      }
      return result;
    } catch (Exception e) {
      throw new DataSourceException("Unable to read data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }

  public boolean insertAdministrator(String userName, String userPassword, String userEmail)
      throws DataSourceException {
    Connection conn = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(INSERT_ADMINISTRATOR);
      stmt.setString(1, userName);
      stmt.setString(2, userPassword);
      stmt.setString(3, userEmail);
      int result = stmt.executeUpdate();
      if (result == 0) {
        return false;
      } else {
        return true;
      }
    } catch (Exception e) {
      throw new DataSourceException("Unable to read data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }

  public boolean deleteAdministrator(long admin_id) throws DataSourceException {
    Connection conn = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(DELETE_ADMINISTRATOR);
      stmt.setLong(1, admin_id);
      int result = stmt.executeUpdate();
      if (result == 0) {
        return false;
      } else {
        return true;
      }
    } catch (Exception e) {
      throw new DataSourceException("Unable to delete data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }
}
