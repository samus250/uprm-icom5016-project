package machete.db.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import machete.db.model.dto.Address;

public class AddressDAO {
  private static final String INSERT_ADDRESS = "insert into address (user_id, address_line1, address_line2, address_line3, "
      + "address_city, address_state, address_country, address_zip, address_alias) "
      + "values (?, ?, ?, ?, ?, ?, ?, ?, ?);";

  private static final String ADDRESSES_BY_USERID = "select address_id, user_id, address_line1, address_line2, address_line3, address_city, address_state, address_country, address_zip, address_alias "
      + "from address where user_id = ?;";

  private static final String ADDRESS_BY_ID = "select address_id, user_id, address_line1, address_line2, address_line3, address_city, address_state, address_country, address_zip, address_alias "
      + "from address where address_id = ?;";

  private static final String UPDATE_ADDRESS_BY_ID = "update address set address_line1=?, address_line2=?, address_line3=?, address_city=?, "
      + "address_state=?, address_country=?, address_zip=?, address_alias=? "
      + "where address_id=?;";

  private static AddressDAO singleton;

  private AddressDAO() {

  }

  public static AddressDAO getInstance() {
    if (singleton == null) {
      singleton = new AddressDAO();
    }
    return singleton;
  }

  public boolean insertAddress(long userId, String line1, String line2, String line3, String city,
      String state, String country, String zip, String alias) throws DataSourceException {
    Connection conn = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(INSERT_ADDRESS);
      stmt.setLong(1, userId);
      stmt.setString(2, line1);
      stmt.setString(3, line2);
      stmt.setString(4, line3);
      stmt.setString(5, city);
      stmt.setString(6, state);
      stmt.setString(7, country);
      stmt.setString(8, zip);
      stmt.setString(9, alias);
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

  public Address getAddress(long addressId) throws DataSourceException {
    Connection conn = null;
    Address result = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(ADDRESS_BY_ID);
      stmt.setLong(1, addressId);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        result = new Address();
        result.setAddressId(rs.getLong(1));
        result.setUserId(rs.getLong(2));
        result.setAddressLine1(rs.getString(3));
        result.setAddressLine2(rs.getString(4));
        result.setAddressLine3(rs.getString(5));
        result.setAddressCity(rs.getString(6));
        result.setAddressState(rs.getString(7));
        result.setAddressCountry(rs.getString(8));
        result.setAddressZip(rs.getString(9));
        result.setAddressAlias(rs.getString(10));
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

  public ArrayList<Address> getAddressesByUserId(long userId) throws DataSourceException {
    Connection conn = null;
    Address result = null;
    ArrayList<Address> results = new ArrayList<Address>();

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(ADDRESSES_BY_USERID);
      stmt.setLong(1, userId);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new Address();
        result.setAddressId(rs.getLong(1));
        result.setUserId(rs.getLong(2));
        result.setAddressLine1(rs.getString(3));
        result.setAddressLine2(rs.getString(4));
        result.setAddressLine3(rs.getString(5));
        result.setAddressCity(rs.getString(6));
        result.setAddressState(rs.getString(7));
        result.setAddressCountry(rs.getString(8));
        result.setAddressZip(rs.getString(9));
        result.setAddressAlias(rs.getString(10));
        results.add(result);
      }
      return results;
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

  public boolean updateAddress(long addressId, String line1, String line2, String line3,
      String city, String state, String country, String zip, String alias)
      throws DataSourceException {
    Connection conn = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(UPDATE_ADDRESS_BY_ID);
      stmt.setString(1, line1);
      stmt.setString(2, line2);
      stmt.setString(3, line3);
      stmt.setString(4, city);
      stmt.setString(5, state);
      stmt.setString(6, country);
      stmt.setString(7, zip);
      stmt.setString(8, alias);
      stmt.setLong(9, addressId);

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
