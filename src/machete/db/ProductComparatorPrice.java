package machete.db;

import machete.db.model.dto.Product;

public class ProductComparatorPrice implements ProductComparator {

  @Override
  public int compare(Product o1, Product o2) {
    Double price1 = o1.getProductPrice();
    Double price2 = o2.getProductPrice();
    return price1.compareTo(price2);
  }

}
