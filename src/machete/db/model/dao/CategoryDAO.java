package machete.db.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import machete.db.model.dto.Category;

public class CategoryDAO {
  private static CategoryDAO singleton = null;
  private static final String CATEGORY_BY_ID = "select category_id, category_name from category where category_id = ?;";
  private static final String INSERT_CATEGORY = "insert into category (category_name) values (?);";
  private static final String ALL_CATEGORIES = "select category_id, category_name from category";
  private static final String DELETE_CATEGORY = "delete from category where category_id = ?";
  private static final String UPDATE_CATEGORY_BY_ID = "update category set category_name=? where category_id=?;";

  private CategoryDAO() {

  }

  public static CategoryDAO getInstance() {
    if (singleton == null) {
      singleton = new CategoryDAO();
    }
    return singleton;
  }

  public Category getCategory(long id) throws DataSourceException {
    Connection conn = null;
    Category result = null;
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(CATEGORY_BY_ID);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        result = new Category();
        result.setCategoryId(rs.getLong(1));
        result.setCategoryName(rs.getString(2));
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

  public ArrayList<Category> getAllCategories() throws DataSourceException {
    Connection conn = null;
    Category result = null;
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(ALL_CATEGORIES);
      ResultSet rs = stmt.executeQuery();
      ArrayList<Category> results = new ArrayList<Category>();
      while (rs.next()) {
        result = new Category();
        result.setCategoryId(rs.getLong(1));
        //
        result.setCategoryName(rs.getString(2));
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

  public boolean insertCategory(String name) throws DataSourceException {
    Connection conn = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(INSERT_CATEGORY);
      stmt.setString(1, name);
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

  public boolean deleteCategory(long category_id) throws DataSourceException {
    Connection conn = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(DELETE_CATEGORY);
      stmt.setLong(1, category_id);
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

  public boolean updateCategory(long cid, String name) throws DataSourceException {
    Connection conn = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(UPDATE_CATEGORY_BY_ID);
      stmt.setString(1, name);
      stmt.setLong(2, cid);
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
