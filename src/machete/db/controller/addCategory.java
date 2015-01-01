package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import machete.db.HeaderFooterUtils;
import machete.db.model.service.CategoryService;

public class addCategory extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String forwardURL = "/admin/notLoggedIn.jsp";

    if (HeaderFooterUtils.adminLoggedIn(request)) {
      // add the category
      String category_name = request.getParameter("categoryname");
      CategoryService service = CategoryService.newInstance();
      CategoryService.add_status status = service.addCategory(category_name);
      if (status == CategoryService.add_status.SUCCESS)
        forwardURL = "/admin/categories/categoryAdded.jsp";
      else
        forwardURL = "/admin/categories/categoryNotAdded.jsp";
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);

  }

}
