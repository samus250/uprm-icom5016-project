package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import machete.db.HeaderFooterUtils;
import machete.db.model.dto.Customer;
import machete.db.model.service.CustomerService;

public class nReadCustomerServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public nReadCustomerServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    HeaderFooterUtils.getHeaderFooterAttributes(request, response);
    String forwardURL = null;

    String userName = request.getParameter("userName");
    String password = request.getParameter("password");

    CustomerService service = CustomerService.newInstance();
    Customer customer = service.getCustomer(userName);

    if (customer == null) {
      forwardURL = "./customer/customerNotFound.jsp";
    } else {
      // check password
      if (customer.getUserPassword().equals(password)) {
        Cookie cookie = new Cookie("userid", Long.toString(customer.getUserId()));
        response.addCookie(cookie);
        session.setAttribute("customer", customer);
        forwardURL = "./login/loggedIn.jsp";
      } else {
        forwardURL = "./customer/customerNotFound.jsp";
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
