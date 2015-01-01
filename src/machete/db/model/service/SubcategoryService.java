package machete.db.model.service;

import java.util.ArrayList;

import machete.db.model.dao.SubcategoryDAO;
import machete.db.model.dto.Subcategory;

public class SubcategoryService {

  public static enum add_status {
    SUCCESS, OTHER_FAILURE
  }

  public static enum remove_status {
    SUCCESS, OTHER_FAILURE
  }

  private SubcategoryService() {

  }

  public static SubcategoryService newInstance() {
    return new SubcategoryService();
  }

  public Subcategory getSubcategory(String subcategory_id) {
    SubcategoryDAO dao = SubcategoryDAO.getInstance();
    // validate stuff
    long id = Long.parseLong(subcategory_id);
    return dao.getSubcategory(id);
  }

  public ArrayList<Subcategory> getSubcategoriesByCategoryId(String category_id) {
    SubcategoryDAO dao = SubcategoryDAO.getInstance();
    long id = Long.parseLong(category_id);
    return dao.getSubcategoriesByCategoryId(id);
  }

  public add_status addSubcategory(String categoryId, String name) {
    SubcategoryDAO dao = SubcategoryDAO.getInstance();
    long cid = Long.parseLong(categoryId);
    boolean success = dao.insertSubcategory(cid, name);
    if (!success)
      return add_status.OTHER_FAILURE;

    return add_status.SUCCESS;
  }

  public remove_status removeSubcategory(String subcategoryId) {
    SubcategoryDAO dao = SubcategoryDAO.getInstance();
    long id = Long.parseLong(subcategoryId);
    boolean success = dao.deleteSubcategory(id);
    if (!success)
      return remove_status.OTHER_FAILURE;

    return remove_status.SUCCESS;
  }

}
