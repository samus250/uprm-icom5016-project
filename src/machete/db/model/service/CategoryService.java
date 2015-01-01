package machete.db.model.service;

import java.util.ArrayList;

import machete.db.model.dao.CategoryDAO;
import machete.db.model.dto.Category;

public class CategoryService {

  public static enum add_status {
    SUCCESS, OTHER_FAILURE
  }

  public static enum remove_status {
    SUCCESS, OTHER_FAILURE
  }

  private CategoryService() {

  }

  public static CategoryService newInstance() {
    return new CategoryService();
  }

  public Category getCategory(String category_id) {
    CategoryDAO dao = CategoryDAO.getInstance();
    // validate stuff
    long id = Long.parseLong(category_id);
    return dao.getCategory(id);
  }

  public ArrayList<Category> getAllCategories() {
    CategoryDAO dao = CategoryDAO.getInstance();
    return dao.getAllCategories();
  }

  public add_status addCategory(String category_name) {
    CategoryDAO dao = CategoryDAO.getInstance();
    boolean success = dao.insertCategory(category_name);
    if (!success)
      return add_status.OTHER_FAILURE;

    return add_status.SUCCESS;
  }

  public remove_status removeCategory(String category_id) {
    CategoryDAO dao = CategoryDAO.getInstance();
    long id = Long.parseLong(category_id);
    boolean success = dao.deleteCategory(id);
    if (!success) {
      return remove_status.OTHER_FAILURE;
    }
    return remove_status.SUCCESS;
  }
}
