package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import machete.db.HeaderFooterUtils;
import machete.db.model.service.CustomerService;

public class removeCustomer extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String forwardURL = "/admin/notLoggedIn.jsp";

    if (HeaderFooterUtils.adminLoggedIn(request)) {
      // remove it
      String customer_id = request.getParameter("customerId");
      CustomerService service = CustomerService.newInstance();
      CustomerService.remove_status status = service.removeCustomer(customer_id);
      if (status == CustomerService.remove_status.SUCCESS)
        forwardURL = "/admin/customers/customerDeleted.jsp";
      else
        forwardURL = "/admin/customers/customerNotDeleted.jsp";
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
