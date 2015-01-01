package machete.db.model.service;

import java.util.ArrayList;

import machete.db.model.dao.SubcategoryOptionDAO;
import machete.db.model.dto.SubcategoryOption;

public class SubcategoryOptionService {

  private SubcategoryOptionService() {

  }

  public static SubcategoryOptionService newInstance() {
    return new SubcategoryOptionService();
  }

  public ArrayList<SubcategoryOption> getSubcategoryOptionsBySubcategoryId(String subcategory_id) {
    SubcategoryOptionDAO dao = SubcategoryOptionDAO.getInstance();
    long id = Long.parseLong(subcategory_id);
    return dao.getSubcategoryOptionsBySubcategoryId(id);
  }

  public ArrayList<String> getProductOptionValues(String option_id) {
    SubcategoryOptionDAO dao = SubcategoryOptionDAO.getInstance();
    long id = Long.parseLong(option_id);
    return dao.getProductOptionValues(id);
  }
}
