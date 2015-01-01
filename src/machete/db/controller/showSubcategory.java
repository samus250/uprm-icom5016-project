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
import machete.db.model.dto.Subcategory;
import machete.db.model.dto.SubcategoryOption;
import machete.db.model.service.ProductService;
import machete.db.model.service.SubcategoryOptionService;
import machete.db.model.service.SubcategoryService;

public class showSubcategory extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public showSubcategory() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // This little guy should send product information to the jsp... ALL product
    // information.
    HttpSession session = request.getSession();
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = null;

    String subcategory_id = request.getParameter("subcategory_id");
    String sort_by = request.getParameter("sort_by");
    String decreasing = request.getParameter("decreasing");
    boolean sortDecreasing = false;
    if (decreasing != null) {
      if (decreasing.equalsIgnoreCase("true")) {
        sortDecreasing = true;
      }
    }

    SubcategoryService service = SubcategoryService.newInstance();
    Subcategory subcategory = service.getSubcategory(subcategory_id);

    if (subcategory == null) {
      forwardURL = "./customer/subcategoryNotFound.jsp";
    } else {
      ProductService productService = ProductService.newInstance();
      ArrayList<Product> products = productService.getProductsBySubcategoryId(subcategory_id);
      // make comparator!!!
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

      session.setAttribute("subcategory", subcategory);
      session.setAttribute("products", products);

      forwardURL = "./showSubcategory.jsp";
    }

    // get all options for this subcategory...
    SubcategoryOptionService optionService = SubcategoryOptionService.newInstance();
    ArrayList<SubcategoryOption> subcategoryOptions = optionService
        .getSubcategoryOptionsBySubcategoryId(subcategory_id);
    session.setAttribute("subcategoryOptions", subcategoryOptions);

    // we should check if we have the option_id parameter and option_value
    // parameter.
    // if we have id but not value, then forward to a jsp asking for the
    // value...
    // if we have both, then we should just take out all other products from the
    // array ist, and forward to
    // showSubcategory.jsp
    String option_id = request.getParameter("option_id");
    String option_value = request.getParameter("option_value");

    // missing value, but have id
    if ((option_id != null && !option_id.equals(""))
        && (option_value == null || option_value.equals(""))) {
      // forward to a jsp asking for the value
      forwardURL = "./showOptionValues.jsp";

      // set the necessary attribute...
      ArrayList<String> productOptionValues = optionService.getProductOptionValues(option_id);
      session.setAttribute("productOptionValues", productOptionValues);

      // also send all other defined parameters as attributes...

    } else if ((option_id != null && !option_id.equals(""))
        && (option_value != null && !option_value.equals(""))) {
      // else if both are defined, segregate the products
      ProductService productService = ProductService.newInstance();
      ArrayList<Product> products = productService.getProductsBySubcategoryIdAndOptionValue(
          subcategory_id, option_value);

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

      session.setAttribute("subcategory", subcategory);
      session.setAttribute("products", products);
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
