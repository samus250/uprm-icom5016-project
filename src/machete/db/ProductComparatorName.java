package machete.db;

import machete.db.model.dto.Product;

public class ProductComparatorName implements ProductComparator {

  @Override
  public int compare(Product o1, Product o2) {
    return o1.getProductName().compareTo(o2.getProductName());
  }

}
