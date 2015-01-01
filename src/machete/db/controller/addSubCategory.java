package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import machete.db.HeaderFooterUtils;
import machete.db.model.service.SubcategoryService;

public class addSubCategory extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String forwardURL = "/admin/notLoggedIn.jsp";

    if (HeaderFooterUtils.adminLoggedIn(request)) {
      String name = request.getParameter("subcategoryname");
      String categoryId = request.getParameter("categoryId");
      SubcategoryService service = SubcategoryService.newInstance();
      SubcategoryService.add_status add_status = service.addSubcategory(categoryId, name);
      if (add_status == SubcategoryService.add_status.SUCCESS)
        forwardURL = "/admin/categories/subcategoryAdded.jsp";
      else
        forwardURL = "/admin/categories/subcategoryNotAdded.jsp";
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

}
