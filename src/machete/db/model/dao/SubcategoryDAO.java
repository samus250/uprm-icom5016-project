package machete.db.model.dao;

import machete.db.model.dto.Subcategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SubcategoryDAO {

  private static SubcategoryDAO singleton = null;

  private static final String SUBCATEGORY_BY_ID = "select subcategory_id, subcategory_name, category_id "
      + "from subcategory where subcategory_id = ?;";

  private static final String SUBCATEGORIES_BY_CATEGORY_ID = "select subcategory_id, subcategory_name, category_id "
      + "from subcategory where category_id = ?;";

  private static final String INSERT_SUBCATEGORY = "insert into subcategory (category_id, subcategory_name) "
      + "values (?, ?);";

  private static final String UPDATE_SUBCATEGORY = "update subcategory set category_id=?, subcategory_name=? where subcategory_id=?;";

  private static final String DELETE_SUBCATEGORY = "delete from subcategory where subcategory_id = ?";

  private SubcategoryDAO() {

  }

  public static SubcategoryDAO getInstance() {
    if (singleton == null) {
      singleton = new SubcategoryDAO();
    }
    return singleton;
  }

  public Subcategory getSubcategory(long id) throws DataSourceException {
    Connection conn = null;
    Subcategory result = null;
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(SUBCATEGORY_BY_ID);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        result = new Subcategory();
        result.setSubcategoryId(rs.getLong(1));
        result.setSubcategoryName(rs.getString(2));
        result.setCategoryId(rs.getLong(3));
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

  public ArrayList<Subcategory> getSubcategoriesByCategoryId(long category_id)
      throws DataSourceException {
    Connection conn = null;
    Subcategory result = null;
    ArrayList<Subcategory> results = new ArrayList<Subcategory>();
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(SUBCATEGORIES_BY_CATEGORY_ID);
      stmt.setLong(1, category_id);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new Subcategory();
        result.setSubcategoryId(rs.getLong(1));
        result.setSubcategoryName(rs.getString(2));
        result.setCategoryId(rs.getLong(3));
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

  public boolean insertSubcategory(long categoryId, String name) throws DataSourceException {
    Connection conn = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(INSERT_SUBCATEGORY);
      stmt.setLong(1, categoryId);
      stmt.setString(2, name);
      int result = stmt.executeUpdate();
      if (result == 0) {
        return false;
      } else {
        return true;
      }
    } catch (Exception e) {
      throw new DataSourceException("Unable to insert data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }

  public boolean deleteSubcategory(long subcategory_id) throws DataSourceException {
    Connection conn = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(DELETE_SUBCATEGORY);
      stmt.setLong(1, subcategory_id);
      int result = stmt.executeUpdate();
      if (result == 0) {
        return false;
      } else {
        return true;
      }
    } catch (Exception e) {
      throw new DataSourceException("Unable to insert data from data source. ", e);
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e2) {

        }
      }
    }
  }

  public boolean updateSubcategory(long subcategoryId, long categoryId, String name)
      throws DataSourceException {
    Connection conn = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(UPDATE_SUBCATEGORY);
      stmt.setLong(1, categoryId);
      stmt.setString(2, name);
      stmt.setLong(3, subcategoryId);
      int result = stmt.executeUpdate();
      if (result == 0) {
        return false;
      } else {
        return true;
      }
    } catch (Exception e) {
      throw new DataSourceException("Unable to insert data from data source. ", e);
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
