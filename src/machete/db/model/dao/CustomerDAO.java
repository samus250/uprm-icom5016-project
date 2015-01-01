package machete.db.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import machete.db.model.dto.Customer;

public class CustomerDAO {
  private static CustomerDAO singleton;

  private static final String CUSTOMER_BY_ID = "select user_id, user_name, user_password, user_email, "
      + "customer_firstname, customer_middlename, customer_lastname "
      + "from customer where user_id = ?";

  private static final String CUSTOMER_BY_USERNAME = "select user_id, user_name, user_password, user_email, "
      + "customer_firstname, customer_middlename, customer_lastname "
      + "from customer where user_name = ?";

  private static final String INSERT_CUSTOMER = "insert into customer (user_name, user_password, user_email, "
      + "customer_firstname, customer_middlename, customer_lastname) "
      + "values (?, ?, ?, ?, ?, ?);";

  private static final String UPDATE_CUSTOMER_BY_ID = "update customer set user_password=?, user_email=?, customer_firstname=?, "
      + "customer_middlename=?, customer_lastname=? where user_id=?;";

  private static final String DELETE_CUSTOMER = "delete from customer where user_id = ?";

  private CustomerDAO() {

  }

  public static CustomerDAO getInstance() {
    if (singleton == null) {
      singleton = new CustomerDAO();
    }
    return singleton;
  }

  public Customer getCustomer(long userId) throws DataSourceException {
    Connection conn = null;
    Customer result = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(CUSTOMER_BY_ID);
      stmt.setLong(1, userId);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        result = new Customer();
        result.setUserId(rs.getLong(1));
        result.setUserName(rs.getString(2));
        result.setUserPassword(rs.getString(3));
        result.setUserEmail(rs.getString(4));
        result.setCustomerFirstname(rs.getString(5));
        result.setCustomerMiddlename(rs.getString(6));
        result.setCustomerLastname(rs.getString(7));
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

  public Customer getCustomerByUsername(String userName) throws DataSourceException {
    Connection conn = null;
    Customer result = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(CUSTOMER_BY_USERNAME);
      stmt.setString(1, userName);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        result = new Customer();
        result.setUserId(rs.getLong(1));
        result.setUserName(rs.getString(2));
        result.setUserPassword(rs.getString(3));
        result.setUserEmail(rs.getString(4));
        result.setCustomerFirstname(rs.getString(5));
        result.setCustomerMiddlename(rs.getString(6));
        result.setCustomerLastname(rs.getString(7));
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

  public boolean insertCustomer(String userName, String userPassword, String userEmail,
      String customerFirstName, String customerMiddleName, String customerLastName)
      throws DataSourceException {
    Connection conn = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(INSERT_CUSTOMER);
      stmt.setString(1, userName);
      stmt.setString(2, userPassword);
      stmt.setString(3, userEmail);
      stmt.setString(4, customerFirstName);
      stmt.setString(5, customerMiddleName);
      stmt.setString(6, customerLastName);
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

  public boolean deleteCustomer(long customer_id) throws DataSourceException {
    Connection conn = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(DELETE_CUSTOMER);
      stmt.setLong(1, customer_id);
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

  public boolean updateCustomer(long customer_id, String userPassword, String userEmail,
      String customerFirstName, String customerMiddleName, String customerLastName)
      throws DataSourceException {
    Connection conn = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(UPDATE_CUSTOMER_BY_ID);
      stmt.setString(1, userPassword);
      stmt.setString(2, userEmail);
      stmt.setString(3, customerFirstName);
      stmt.setString(4, customerMiddleName);
      stmt.setString(5, customerLastName);
      stmt.setLong(6, customer_id);

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
}
