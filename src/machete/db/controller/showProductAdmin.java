package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import machete.db.HeaderFooterUtils;
import machete.db.model.dto.Product;
import machete.db.model.service.ProductService;

public class showProductAdmin extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public showProductAdmin() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = "/admin/notLoggedIn.jsp";

    String product_id = request.getParameter("product_id");

    if (HeaderFooterUtils.adminLoggedIn(request)) {
      ProductService service = ProductService.newInstance();
      Product product = service.getProduct(product_id);

      if (product == null) {
        forwardURL = "/admin/products/productNotFound.jsp";
      } else {
        session.setAttribute("product", product);
        forwardURL = "/admin/showProductAdmin.jsp";
      }
    }
    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
