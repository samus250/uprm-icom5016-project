package machete.db.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import machete.db.model.dto.Creditcard;

public class CreditcardDAO {
  private static CreditcardDAO singleton;

  private static final String INSERT_CREDITCARD = "insert into creditcard (user_id, address_id, creditcard_number, creditcard_securitynumber, "
      + "creditcard_month, creditcard_year, creditcard_alias) values (?, ?, ?, ?, ?, ?, ?);";

  private static final String CREDITCARD_BY_ID = "select creditcard_id, user_id, address_id, creditcard_number, creditcard_securitynumber, creditcard_month, creditcard_year, creditcard_alias "
      + "from creditcard where creditcard_id = ?;";

  private static final String CREDITCARDS_BY_USERID = "select creditcard_id, user_id, address_id, creditcard_number, creditcard_securitynumber, creditcard_month, creditcard_year, creditcard_alias "
      + "from creditcard where user_id = ?;";

  private static final String UPDATE_CREDITCARD_BY_ID = "update creditcard set address_id=?, creditcard_number=?, "
      + "creditcard_securitynumber=?, creditcard_month=?, creditcard_year=?, creditcard_alias=? "
      + "where creditcard_id=?;";

  private CreditcardDAO() {

  }

  public static CreditcardDAO getInstance() {
    if (singleton == null) {
      singleton = new CreditcardDAO();
    }
    return singleton;
  }

  public boolean insertCreditcard(long userId, long addressId, String number,
      String securityNumber, String month, String year, String alias) throws DataSourceException {
    Connection conn = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(INSERT_CREDITCARD);
      stmt.setLong(1, userId);
      stmt.setLong(2, addressId);
      stmt.setString(3, number);
      stmt.setString(4, securityNumber);
      stmt.setString(5, month);
      stmt.setString(6, year);
      stmt.setString(7, alias);

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

  public Creditcard getCreditcard(long creditcardId) throws DataSourceException {
    Connection conn = null;
    Creditcard result = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(CREDITCARD_BY_ID);
      stmt.setLong(1, creditcardId);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        result = new Creditcard();
        result.setCreditcardId(rs.getLong(1));
        result.setUserId(rs.getLong(2));
        result.setAddressId(rs.getLong(3));
        result.setCreditcardNumber(rs.getString(4));
        result.setCreditcardSecuritynumber(rs.getString(5));
        result.setCreditcardMonth(rs.getString(6));
        result.setCreditcardYear(rs.getString(7));
        result.setCreditcardAlias(rs.getString(8));
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

  public ArrayList<Creditcard> getCreditCardsByUserId(long userId) throws DataSourceException {
    Connection conn = null;
    Creditcard result = null;
    ArrayList<Creditcard> results = new ArrayList<Creditcard>();

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(CREDITCARDS_BY_USERID);
      stmt.setLong(1, userId);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new Creditcard();
        result.setCreditcardId(rs.getLong(1));
        result.setUserId(rs.getLong(2));
        result.setAddressId(rs.getLong(3));
        result.setCreditcardNumber(rs.getString(4));
        result.setCreditcardSecuritynumber(rs.getString(5));
        result.setCreditcardMonth(rs.getString(6));
        result.setCreditcardYear(rs.getString(7));
        result.setCreditcardAlias(rs.getString(8));
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

  public boolean updateCreditcard(long cardId, long addressId, String number,
      String securityNumber, String month, String year, String alias) throws DataSourceException {
    Connection conn = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(UPDATE_CREDITCARD_BY_ID);
      stmt.setLong(1, addressId);
      stmt.setString(2, number);
      stmt.setString(3, securityNumber);
      stmt.setString(4, month);
      stmt.setString(5, year);
      stmt.setString(6, alias);
      stmt.setLong(7, cardId);

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
