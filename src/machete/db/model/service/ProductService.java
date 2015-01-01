package machete.db.model.service;

import java.util.ArrayList;

import machete.db.model.dao.ProductDAO;
import machete.db.model.dto.Product;

public class ProductService {

  public static enum add_status {
    SUCCESS, OTHER_FAILURE
  }

  public static enum remove_status {
    SUCCESS, OTHER_FAILURE
  }

  private ProductService() {

  }

  public static ProductService newInstance() {
    return new ProductService();
  }

  public Product getProduct(String product_id) {
    ProductDAO dao = ProductDAO.getInstance();
    // validate stuff
    long id = Long.parseLong(product_id);
    return dao.getProduct(id);
  }

  public Product getProduct(long product_id) {
    ProductDAO dao = ProductDAO.getInstance();
    return dao.getProduct(product_id);
  }

  public ArrayList<Product> getProductsBySubcategoryId(String subcategory_id) {
    ProductDAO dao = ProductDAO.getInstance();
    long id = Long.parseLong(subcategory_id);
    return dao.getProductsBySubcategoryId(id);
  }

  public ArrayList<Product> getProductsBySubcategoryIdAndOptionValue(String subcategory_id,
      String option_value) {
    ProductDAO dao = ProductDAO.getInstance();
    long id = Long.parseLong(subcategory_id);
    return dao.getProductsBySubccategoryIdAndOptionValue(id, option_value);
  }

  public Product getNewestProduct() {
    ProductDAO dao = ProductDAO.getInstance();
    return dao.getNewestProduct();
  }

  public add_status addProduct(String subcategory_id, String name, String brand, String price,
      String description, String model, String photo) {
    ProductDAO dao = ProductDAO.getInstance();
    long s_id = Long.parseLong(subcategory_id);
    double p = Double.parseDouble(price);
    boolean success = dao.insertProduct(s_id, name, brand, p, description, model, photo);
    if (!success)
      return add_status.OTHER_FAILURE;
    return add_status.SUCCESS;
  }

  public remove_status removeProduct(String productId) {
    ProductDAO dao = ProductDAO.getInstance();
    long id = Long.parseLong(productId);
    boolean success = dao.deleteProduct(id);
    if (!success)
      return remove_status.OTHER_FAILURE;
    return remove_status.SUCCESS;
  }

  public ArrayList<Product> getProductsBySearch(String search) {
    ProductDAO dao = ProductDAO.getInstance();
    return dao.getProductsBySearch(search);
  }
}
