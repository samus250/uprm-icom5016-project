package machete.db.model.service;

import machete.db.Pair;
import machete.db.model.dto.Product;

public class ShoppingCartTester {

  /**
   * @param args
   */
  public static void main(String[] args) {
    String cartString = "1,1:2,1:3,2:4,2:";
    ShoppingCart cart = new ShoppingCart(cartString);

    System.out.println(cart.getShoppingCartString());
    System.out.println();

    for (Pair<Long, Long> pair : cart.getProductIdQuantityPairs()) {
      System.out.println(pair);
    }
    System.out.println();

    cart.addToShoppingCart(5, 10);
    cart.addToShoppingCart(30, 3);
    cart.addToShoppingCart(50, 1);
    cart.addToShoppingCart(29, 10);
    cart.addToShoppingCart(33, 10);
    cart.addToShoppingCart(6, 10);

    cart.addToShoppingCart(5, 10);
    cart.setProductQuantity(33, 15);

    cart.removeFromShoppingCart(5);
    cart.removeFromShoppingCart(3);
    cart.addToShoppingCart(1, -3000);
    cart.addToShoppingCart(2, -3000);
    System.out.println(cart.getShoppingCartString());

    for (Pair<Long, Long> pair : cart.getProductIdQuantityPairs()) {
      System.out.println(pair);
    }
    System.out.println();

    for (Pair<Product, Long> pair : cart.getProductQuantityPairs()) {
      System.out.println(pair);
    }
  }

}
