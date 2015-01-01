package machete.db.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import machete.db.model.dto.SubcategoryOption;

public class SubcategoryOptionDAO {

  private static SubcategoryOptionDAO singleton = null;

  private static final String SUBCATEGORY_OPTIONS_BY_SUBCATEGORY_ID = "select subcategory_id, option_name, subcategory_option_id "
      + "from subcategory_option " + "where subcategory_id = ?;";

  private static final String PRODUCT_OPTION_VALUES_BY_OPTION_ID = "select distinct option_value "
      + "from product_option natural join subcategory_option " + "where subcategory_option_id = ?;";

  private SubcategoryOptionDAO() {

  }

  public static SubcategoryOptionDAO getInstance() {
    if (singleton == null) {
      singleton = new SubcategoryOptionDAO();
    }
    return singleton;
  }

  public ArrayList<SubcategoryOption> getSubcategoryOptionsBySubcategoryId(long subcategory_id)
      throws DataSourceException {
    Connection conn = null;
    SubcategoryOption result = null;
    ArrayList<SubcategoryOption> results = new ArrayList<SubcategoryOption>();
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(SUBCATEGORY_OPTIONS_BY_SUBCATEGORY_ID);
      stmt.setLong(1, subcategory_id);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new SubcategoryOption();
        result.setSubcategoryId(rs.getLong(1));
        result.setOptionName(rs.getString(2));
        result.setSubcategoryOptionId(rs.getLong(3));
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

  public ArrayList<String> getProductOptionValues(long option_id) throws DataSourceException {
    Connection conn = null;
    ArrayList<String> results = new ArrayList<String>();
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(PRODUCT_OPTION_VALUES_BY_OPTION_ID);
      stmt.setLong(1, option_id);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        results.add(rs.getString(1));
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
}
