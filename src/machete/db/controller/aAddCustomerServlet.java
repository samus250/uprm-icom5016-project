package machete.db.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import machete.db.model.service.CustomerService;

public class aAddCustomerServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    String firstName = request.getParameter("firstname");
    String middleName = request.getParameter("middlename");
    String lastName = request.getParameter("lastname");
    String userName = request.getParameter("username");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String verifyPassword = request.getParameter("verify");

    // validate everything in the service
    CustomerService service = CustomerService.newInstance();
    CustomerService.add_status status = service.addCustomer(firstName, middleName, lastName,
        userName, email, password, verifyPassword);

    String forwardURL;
    if (status == CustomerService.add_status.SUCCESS) {
      forwardURL = "./admin/customers/successfullyRegistered.jsp";
    } else {
      // what was the error?
      String errorString;
      if (status == CustomerService.add_status.USERNAME_TAKEN)
        errorString = "Username Already Taken";
      else if (status == CustomerService.add_status.PASSWORD_VERIFICATION_ERROR)
        errorString = "Password Verification Failed.";
      else
        errorString = "Other Failure. System Error";

      session.setAttribute("errorString", errorString);
      forwardURL = "./admin/customers/notRegistered.jsp";
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
    dispatcher.forward(request, response);
  }

}
