package machete.db;

public class ProductComparatorMaker {
  public static ProductComparator makeComparator(String s) {
    if (s.equals("name"))
      return new ProductComparatorName();

    if (s.equals("price"))
      return new ProductComparatorPrice();

    if (s.equalsIgnoreCase("brand"))
      return new ProductComparatorBrand();

    return new ProductComparatorPrice();
  }
}
