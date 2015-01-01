package machete.db.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import machete.db.HeaderFooterUtils;
import machete.db.model.dto.Product;
import machete.db.model.dto.Subcategory;
import machete.db.model.service.ProductService;
import machete.db.model.service.SubcategoryService;

public class showSubcategoryAdmin extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public showSubcategoryAdmin() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // This little guy should send product information to the jsp... ALL product
    // information.
    HttpSession session = request.getSession();
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = "/admin/notLoggedIn.jsp";

    if (HeaderFooterUtils.adminLoggedIn(request)) {
      String subcategory_id = request.getParameter("subcategory_id");

      SubcategoryService service = SubcategoryService.newInstance();
      Subcategory subcategory = service.getSubcategory(subcategory_id);

      if (subcategory == null) {
        forwardURL = "./admin/categories/subcategoryNotFound.jsp";
      } else {
        ProductService productService = ProductService.newInstance();
        ArrayList<Product> products = productService.getProductsBySubcategoryId(subcategory_id);
        session.setAttribute("subcategory", subcategory);
        session.setAttribute("products", products);

        forwardURL = "/admin/showSubcategoryAdmin.jsp";
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
