package machete.db.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import machete.db.HeaderFooterUtils;
import machete.db.Pair;
import machete.db.model.dto.Product;
import machete.db.model.service.ShoppingCart;

public class shoppingCart extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public shoppingCart() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = null;
    String shoppingCartString = null;

    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("shoppingCart")) {
        shoppingCartString = cookie.getValue();
      }
    }

    if (shoppingCartString == null || shoppingCartString.equals("")) {
      forwardURL = "shopping_cart/shoppingCartEmpty.jsp";
    } else {
      ShoppingCart shoppingCart = new ShoppingCart(shoppingCartString);
      ArrayList<Pair<Product, Long>> productQuantityPairs = shoppingCart.getProductQuantityPairs();
      session.setAttribute("productQuantityPairs", productQuantityPairs);
      forwardURL = "shopping_cart/shoppingCart.jsp";
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
