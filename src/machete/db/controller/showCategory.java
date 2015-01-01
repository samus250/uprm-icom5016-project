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
import machete.db.model.dto.Category;
import machete.db.model.dto.Subcategory;
import machete.db.model.service.CategoryService;
import machete.db.model.service.SubcategoryService;

public class showCategory extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public showCategory() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = null;

    String category_id = request.getParameter("category_id");

    CategoryService service = CategoryService.newInstance();
    Category category = service.getCategory(category_id);

    if (category == null) {
      forwardURL = "./customer/categoryNotFound.jsp";
    } else {
      SubcategoryService subcategoryService = SubcategoryService.newInstance();
      ArrayList<Subcategory> subcategories = subcategoryService
          .getSubcategoriesByCategoryId(category_id);
      session.setAttribute("category", category);
      session.setAttribute("subcategories", subcategories);

      forwardURL = "./showCategory.jsp";
    }
    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
