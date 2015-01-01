package machete.db.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import machete.db.HeaderFooterUtils;
import machete.db.ProductComparator;
import machete.db.ProductComparatorMaker;
import machete.db.model.dto.Product;
import machete.db.model.service.ProductService;

public class ProductSearch extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public ProductSearch() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = null;

    String search = request.getParameter("search");
    String sort_by = request.getParameter("sort_by");
    String decreasing = request.getParameter("decreasing");
    boolean sortDecreasing = false;
    if (decreasing != null) {
      if (decreasing.equalsIgnoreCase("true")) {
        sortDecreasing = true;
      }
    }

    ProductService service = ProductService.newInstance();
    ArrayList<Product> products = service.getProductsBySearch(search);

    if (products == null) {
      forwardURL = "/ICOM5016_Project/admin/products/productNotFound.jsp";
    } else if (products.isEmpty()) {
      forwardURL = "/ICOM5016_Project/admin/products/productNotFound.jsp";
    } else {
      if (sort_by != null) {
        ProductComparator comparator = ProductComparatorMaker.makeComparator(sort_by);
        Collections.sort(products, comparator);
      }

      if (sortDecreasing) {
        ArrayList<Product> temp = new ArrayList<Product>();
        for (int i = products.size() - 1; i >= 0; i--) {
          temp.add(products.get(i));
        }
        products = temp;
      }
      session.setAttribute("searchProducts", products);
      session.setAttribute("searchString", search);
      forwardURL = "/admin/products/searchResult.jsp";
    }
    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
