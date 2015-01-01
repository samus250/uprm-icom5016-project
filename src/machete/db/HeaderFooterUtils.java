package machete.db;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import machete.db.model.dto.Category;
import machete.db.model.dto.Product;
import machete.db.model.service.CategoryService;
import machete.db.model.service.ProductService;
import machete.db.model.service.ShoppingCart;

public class HeaderFooterUtils {

  public static String getUserLoggedIn(HttpServletRequest request) {
    String result = null;
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("userid")) {
        result = cookie.getValue();
      }
    }
    return result;
  }

  public static boolean userLoggedIn(HttpServletRequest request) {
    if (getUserLoggedIn(request) == null)
      return false;
    return true;
  }

  public static String getAdminLoggedIn(HttpServletRequest request) {
    String result = null;
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("adminid")) {
        result = cookie.getValue();
      }
    }
    return result;
  }

  public static boolean adminLoggedIn(HttpServletRequest request) {
    if (getAdminLoggedIn(request) == null)
      return false;
    return true;
  }

  public static void getHeaderFooterAttributes(HttpServletRequest request,
      HttpServletResponse response) {
    HttpSession session = request.getSession();

    // category
    CategoryService service = CategoryService.newInstance();
    ArrayList<Category> allCategories = service.getAllCategories();
    session.setAttribute("categories", allCategories);

    // shopping cart statistics
    String shoppingCartString = null;

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("shoppingCart")) {
          shoppingCartString = cookie.getValue();
        }
      }
    }
    if (shoppingCartString == null || shoppingCartString.equals("")) {
      session.setAttribute("shoppingCartItemCount", 0);
      session.setAttribute("shoppingCartTotal", 0.0);
    } else {
      ShoppingCart shoppingCart = new ShoppingCart(shoppingCartString);
      ArrayList<Pair<Product, Long>> productQuantityPairs = shoppingCart.getProductQuantityPairs();

      // calculate price
      double totalPrice = 0;
      for (Pair<Product, Long> pair : productQuantityPairs) {
        totalPrice += pair.getFirst().getProductPrice() * pair.getSecond();
      }

      session.setAttribute("shoppingCartItemCount", productQuantityPairs.size());
      session.setAttribute("shoppingCartTotal", totalPrice);
    }

    // select a random product and throw it to the special products widget
    // also select the last product and throw it to the new product widget.
    ProductService productService = ProductService.newInstance();
    Product newestProduct = productService.getNewestProduct();
    session.setAttribute("newestProduct", newestProduct);
    long newestProductId = newestProduct.getProductId();

    Product randomProduct = null;
    do {
      Random rand = new Random();
      int randInt = 0;
      if (newestProductId > Integer.MAX_VALUE)
        randInt = rand.nextInt();
      else
        randInt = rand.nextInt((int) newestProductId);

      // check if it really exists
      try {
        randomProduct = productService.getProduct(randInt);
      } catch (Exception e) {
        continue;
      }

    } while (randomProduct == null);

    session.setAttribute("randomProduct", randomProduct);
  }
}
