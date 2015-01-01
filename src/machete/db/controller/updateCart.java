package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import machete.db.HeaderFooterUtils;
import machete.db.model.service.ShoppingCart;

public class updateCart extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public updateCart() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = null;
    String shoppingCartString = null;

    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("shoppingCart")) {
        shoppingCartString = cookie.getValue();
      }
    }

    // if customer has no shopping cart yet, then nothing should be done
    if (shoppingCartString != null) {
      ShoppingCart shoppingCart = new ShoppingCart(shoppingCartString);
      // add the stuff to the cart
      String productId = request.getParameter("product_id");
      String quantity = request.getParameter("quantity");

      shoppingCart.setProductQuantity(Long.parseLong(productId), Long.parseLong(quantity));
      shoppingCartString = shoppingCart.getShoppingCartString();
      Cookie cartCookie = new Cookie("shoppingCart", shoppingCartString);
      response.addCookie(cartCookie);

      if (Long.parseLong(quantity) == 0) {
        forwardURL = "shopping_cart/productRemoved.jsp";
      } else {
        forwardURL = "shopping_cart/quantityUpdated.jsp";
      }
    } else {
      forwardURL = "shoppingCart";
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
