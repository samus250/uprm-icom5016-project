package machete.db.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import machete.db.model.dto.Product;

public class ProductDAO {

  private static ProductDAO singleton = null;

  private static final String PRODUCT_BY_ID = "select subcategory_id, product_id, product_name, product_price, product_brand, product_description, "
      + "product_model, product_photo from product where product_id = ?;";

  private static final String PRODUCTS_BY_SUBCATEGORY_ID = "select subcategory_id, product_id, product_name, product_price, product_brand, product_description, "
      + "product_model, product_photo from product where subcategory_id = ?;";

  private static final String NEWEST_PRODUCT = "select subcategory_id, product_id, product_name, product_price, product_brand, product_description, "
      + "product_model, product_photo from product "
      + "where product_id = ("
      + "select max(product_id) from product);";

  private static final String PRODUCTS_BY_SUBCATEGORY_ID_AND_OPTION_VALUE = "select subcategory_id, product_id, product_name, product_price, product_brand, product_description, "
      + "product_model, product_photo "
      + "from product natural join product_option "
      + "where subcategory_id = ? " + "and option_value = ?;";

  private static final String INSERT_PRODUCT = "insert into product (subcategory_id, product_name, product_price, product_brand, product_description, "
      + "product_model, product_photo) values (?, ?, ?, ?, ?, ?, ?);";

  private static final String DELETE_PRODUCT = "delete from product where product_id = ?";

  private static final String PRODUCTS_BY_SEARCH = "select subcategory_id, product_id, product_name, product_price, product_brand, product_description, "
      + "product_model, product_photo "
      + "from product "
      + "where product_name ilike ? "
      + "OR product_brand ilike ?;";

  private ProductDAO() {

  }

  public static ProductDAO getInstance() {
    if (singleton == null) {
      singleton = new ProductDAO();
    }
    return singleton;
  }

  public Product getProduct(long id) throws DataSourceException {
    Connection conn = null;
    Product result = null;
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(PRODUCT_BY_ID);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        result = new Product();
        result.setSubcategoryId(rs.getLong(1));
        result.setProductId(rs.getLong(2));
        result.setProductName(rs.getString(3));
        result.setProductPrice(rs.getDouble(4));
        result.setProductBrand(rs.getString(5));
        result.setProductDescription(rs.getString(6));
        result.setProductModel(rs.getString(7));
        result.setProductPhoto(rs.getString(8));
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

  public ArrayList<Product> getProductsBySubcategoryId(long subcategory_id)
      throws DataSourceException {
    Connection conn = null;
    Product result = null;
    ArrayList<Product> results = new ArrayList<Product>();
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(PRODUCTS_BY_SUBCATEGORY_ID);
      stmt.setLong(1, subcategory_id);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new Product();
        result.setSubcategoryId(rs.getLong(1));
        result.setProductId(rs.getLong(2));
        result.setProductName(rs.getString(3));
        result.setProductPrice(rs.getDouble(4));
        result.setProductBrand(rs.getString(5));
        result.setProductDescription(rs.getString(6));
        result.setProductModel(rs.getString(7));
        result.setProductPhoto(rs.getString(8));
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

  public Product getNewestProduct() throws DataSourceException {
    Connection conn = null;
    Product result = null;
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(NEWEST_PRODUCT);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        result = new Product();
        result.setSubcategoryId(rs.getLong(1));
        result.setProductId(rs.getLong(2));
        result.setProductName(rs.getString(3));
        result.setProductPrice(rs.getDouble(4));
        result.setProductBrand(rs.getString(5));
        result.setProductDescription(rs.getString(6));
        result.setProductModel(rs.getString(7));
        result.setProductPhoto(rs.getString(8));
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

  public ArrayList<Product> getProductsBySubccategoryIdAndOptionValue(long subcategory_id,
      String option_value) {
    Connection conn = null;
    Product result = null;
    ArrayList<Product> results = new ArrayList<Product>();
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(PRODUCTS_BY_SUBCATEGORY_ID_AND_OPTION_VALUE);
      stmt.setLong(1, subcategory_id);
      stmt.setString(2, option_value);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new Product();
        result.setSubcategoryId(rs.getLong(1));
        result.setProductId(rs.getLong(2));
        result.setProductName(rs.getString(3));
        result.setProductPrice(rs.getDouble(4));
        result.setProductBrand(rs.getString(5));
        result.setProductDescription(rs.getString(6));
        result.setProductModel(rs.getString(7));
        result.setProductPhoto(rs.getString(8));
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

  public boolean insertProduct(long subcategory_id, String name, String brand, double price,
      String description, String model, String photo) throws DataSourceException {
    Connection conn = null;

    try {
      System.out.println("Price: " + price);
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(INSERT_PRODUCT);
      stmt.setLong(1, subcategory_id);
      stmt.setString(2, name);
      stmt.setDouble(3, price);
      stmt.setString(4, brand);
      stmt.setString(5, description);
      stmt.setString(6, model);
      stmt.setString(7, photo);
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

  public boolean deleteProduct(long product_id) throws DataSourceException {
    Connection conn = null;

    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(DELETE_PRODUCT);
      stmt.setLong(1, product_id);
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

  public ArrayList<Product> getProductsBySearch(String search) throws DataSourceException {
    Connection conn = null;
    Product result = null;
    ArrayList<Product> results = new ArrayList<Product>();
    try {
      conn = DataSource.getInstance().getJDBCConnection();
      PreparedStatement stmt = conn.prepareStatement(PRODUCTS_BY_SEARCH);
      // modify search string a bit

      String searchString = "%" + search + "%";
      stmt.setString(1, searchString);
      stmt.setString(2, searchString);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = new Product();
        result.setSubcategoryId(rs.getLong(1));
        result.setProductId(rs.getLong(2));
        result.setProductName(rs.getString(3));
        result.setProductPrice(rs.getDouble(4));
        result.setProductBrand(rs.getString(5));
        result.setProductDescription(rs.getString(6));
        result.setProductModel(rs.getString(7));
        result.setProductPhoto(rs.getString(8));
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
}
