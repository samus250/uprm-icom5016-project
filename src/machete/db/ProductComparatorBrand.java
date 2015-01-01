package machete.db;

import machete.db.model.dto.Product;

public class ProductComparatorBrand implements ProductComparator {

  @Override
  public int compare(Product o1, Product o2) {
    return o1.getProductBrand().compareTo(o2.getProductBrand());
  }

}
