package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import machete.db.HeaderFooterUtils;
import machete.db.model.dto.Customer;
import machete.db.model.service.CustomerService;

public class viewCustomerAccount extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public viewCustomerAccount() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = "/admin/notLoggedIn.jsp";
    String userId = request.getParameter("userid");

    CustomerService service = CustomerService.newInstance();
    Customer customer = service.getCustomerById(userId);

    if (HeaderFooterUtils.adminLoggedIn(request)) {
      if (customer == null) {
        forwardURL = "./admin/customers/customerAccountNotFound.jsp";
      } else {
        // get customer info and throw it to jsp
        session.setAttribute("customer", customer);
        forwardURL = "./admin/customers/customerAccount.jsp";
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
