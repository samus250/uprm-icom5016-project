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

public class addToCart extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public addToCart() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = null;
    String shoppingCartString = "";

    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("shoppingCart")) {
        shoppingCartString = cookie.getValue();
      }
    }

    // if customer has no shopping cart yet, then it will be "", and thus
    // created.

    ShoppingCart shoppingCart = new ShoppingCart(shoppingCartString);
    // add the stuff to the cart
    String productId = request.getParameter("product_id");
    String quantity = request.getParameter("quantity");

    shoppingCart.addToShoppingCart(Long.parseLong(productId), Long.parseLong(quantity));
    shoppingCartString = shoppingCart.getShoppingCartString();
    Cookie cartCookie = new Cookie("shoppingCart", shoppingCartString);
    response.addCookie(cartCookie);
    forwardURL = "shopping_cart/productAdded.jsp";
    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
