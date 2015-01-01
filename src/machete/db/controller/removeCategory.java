package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import machete.db.HeaderFooterUtils;
import machete.db.model.service.CategoryService;

public class removeCategory extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String forwardURL = "/admin/notLoggedIn.jsp";

    if (HeaderFooterUtils.adminLoggedIn(request)) {
      // remove it
      String category_id = request.getParameter("categoryId");
      CategoryService service = CategoryService.newInstance();
      CategoryService.remove_status status = service.removeCategory(category_id);
      if (status == CategoryService.remove_status.SUCCESS)
        forwardURL = "/admin/categories/categoryDeleted.jsp";
      else
        forwardURL = "/admin/categories/categoryNotDeleted.jsp";
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
