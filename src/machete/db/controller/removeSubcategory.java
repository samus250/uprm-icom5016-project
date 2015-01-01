package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import machete.db.HeaderFooterUtils;
import machete.db.model.service.SubcategoryService;

public class removeSubcategory extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String forwardURL = "/admin/notLoggedIn.jsp";

    if (HeaderFooterUtils.adminLoggedIn(request)) {
      // remove it
      String subcategory_id = request.getParameter("subcategoryId");
      SubcategoryService service = SubcategoryService.newInstance();
      SubcategoryService.remove_status status = service.removeSubcategory(subcategory_id);
      if (status == SubcategoryService.remove_status.SUCCESS)
        forwardURL = "/admin/categories/subcategoryDeleted.jsp";
      else
        forwardURL = "/admin/categories/subcategoryNotDeleted.jsp";
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
