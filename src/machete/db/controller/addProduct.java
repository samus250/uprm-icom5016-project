package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import machete.db.HeaderFooterUtils;
import machete.db.model.service.ProductService;

public class addProduct extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String forwardURL = "/admin/notLoggedIn.jsp";

    if (HeaderFooterUtils.adminLoggedIn(request)) {
      String subcategory_id = request.getParameter("sId");
      String name = request.getParameter("name");
      String brand = request.getParameter("brand");
      String price = request.getParameter("price");
      String model = request.getParameter("model");
      String description = request.getParameter("description");
      String photo = request.getParameter("photo");

      ProductService service = ProductService.newInstance();
      ProductService.add_status status = service.addProduct(subcategory_id, name, brand, price,
          description, model, photo);
      if (status == ProductService.add_status.SUCCESS) {
        forwardURL = "/admin/products/productWasAdded.jsp";
      } else {
        forwardURL = "/admin/products/productWasNotAdded.jsp";
      }
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

}
