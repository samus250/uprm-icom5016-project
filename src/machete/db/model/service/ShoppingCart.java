package machete.db.model.service;

import java.util.ArrayList;

import machete.db.Pair;
import machete.db.model.dto.Product;

public class ShoppingCart {
  private ArrayList<Pair<Long, Long>> productIdQuantityPairs;
  private String shoppingCartString;
  private final String PRIMARY_DELIMITER = ":";
  private final String SECONDARY_DELIMITER = ",";

  public ShoppingCart(String s) {
    if (s == null)
      s = "";
    productIdQuantityPairs = new ArrayList<Pair<Long, Long>>();
    shoppingCartString = "";
    setShoppingCartString(s);
  }

  public ArrayList<Pair<Long, Long>> getProductIdQuantityPairs() {
    return productIdQuantityPairs;
  }

  public ArrayList<Pair<Product, Long>> getProductQuantityPairs() {
    // for now, generate them on the fly (speedy constructor, update, etc.)
    ArrayList<Pair<Product, Long>> productQuantityPairs = new ArrayList<Pair<Product, Long>>();
    ProductService service = ProductService.newInstance();
    for (Pair<Long, Long> pair : productIdQuantityPairs) {
      Product product = service.getProduct(pair.getFirst());
      productQuantityPairs.add(new Pair<Product, Long>(product, pair.getSecond()));
    }

    return productQuantityPairs;
  }

  public String getShoppingCartString() {
    return shoppingCartString;
  }

  private void setShoppingCartString(String s) {
    // remove all pairs
    productIdQuantityPairs = new ArrayList<Pair<Long, Long>>();
    // reset shoppingCartString
    shoppingCartString = simplifyShoppingCartString(s);

    // populate pairs
    String[] sPairs = shoppingCartString.split(PRIMARY_DELIMITER);
    for (String sp : sPairs) {
      String[] splitSp = sp.split(SECONDARY_DELIMITER);
      Long productId = 0L;
      Long quantity = 0L;
      try {
        productId = Long.parseLong(splitSp[0]);
        quantity = Long.parseLong(splitSp[1]);
      } catch (Exception e) {
        continue; // they are not longs
      }
      productIdQuantityPairs.add(new Pair<Long, Long>(productId, quantity));
    }
  }

  private void updateShoppingCartString() {
    // generate a string from pairs, set the string without modifying pairs
    // again
    String result = "";
    for (Pair<Long, Long> pair : productIdQuantityPairs) {
      result += pair.getFirst() + SECONDARY_DELIMITER + pair.getSecond() + PRIMARY_DELIMITER;
    }
    shoppingCartString = result;
  }

  private String simplifyShoppingCartString(String s) {
    // TODO do some validation first I suppose...

    ArrayList<Pair<Long, Long>> pairs = new ArrayList<Pair<Long, Long>>();
    String[] sPairs = s.split(PRIMARY_DELIMITER);
    for (String sp : sPairs) {
      if (sp.equals(""))
        continue; // might be empty string on last ';'

      String[] splitSp = sp.split(SECONDARY_DELIMITER);
      Long productId = Long.parseLong(splitSp[0]);
      Long quantity = Long.parseLong(splitSp[1]);

      // if the product is already in a pair, update it's quantity, do not add a
      // new pair
      boolean addNewPair = true;
      for (int i = 0; i < pairs.size(); i++) {
        if (pairs.get(i).getFirst().equals(productId)) {
          quantity += pairs.get(i).getSecond();
          if (quantity > 0) {
            // update it in the pair, notice it might be negative
            pairs.get(i).setSecond(quantity);
          }

          addNewPair = false;
        }
      }
      if (addNewPair) {
        pairs.add(new Pair<Long, Long>(productId, quantity));
      }
    }

    // now delete pairs with negative quantities
    for (int i = 0; i < pairs.size(); i++) {
      if (pairs.get(i).getSecond() <= 0) {
        pairs.remove(i);
        i--;
      }
    }

    // now generate string from pairs
    String result = "";
    for (Pair<Long, Long> pair : pairs) {
      result += pair.getFirst() + SECONDARY_DELIMITER + pair.getSecond() + PRIMARY_DELIMITER;
    }

    return result;
  }

  public void addToShoppingCart(long productId, long quantity) {
    updateProductQuantity(productId, quantity);
  }

  public void removeFromShoppingCart(long productId) {
    setProductQuantity(productId, 0);
  }

  public long getProductQuantity(long productId) {
    for (Pair<Long, Long> pair : productIdQuantityPairs) {
      if (pair.getFirst().equals(productId)) {
        return pair.getSecond();
      }
    }
    return 0;
  }

  public void setProductQuantity(long productId, long productQuantity) {
    boolean productFound = false;
    for (int i = 0; i < productIdQuantityPairs.size(); i++) {
      Pair<Long, Long> pair = productIdQuantityPairs.get(i);
      if (pair.getFirst().equals(productId)) {
        // the product was found, and exists
        productFound = true;
        if (productQuantity > 0)
          pair.setSecond(productQuantity);
        else {
          productIdQuantityPairs.remove(i);
          i--;
        }
      }
    }
    if (productFound)
      updateShoppingCartString();
    else {
      // add the product to the end of the string and set it
      String concat = shoppingCartString.concat(productId + SECONDARY_DELIMITER + productQuantity
          + PRIMARY_DELIMITER);
      setShoppingCartString(concat);
    }
  }

  public void updateProductQuantity(long productId, long quantity) {
    setProductQuantity(productId, getProductQuantity(productId) + quantity);
  }
}
